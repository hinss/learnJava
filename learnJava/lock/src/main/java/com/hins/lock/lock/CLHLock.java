package com.hins.lock.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author: hins
 * @created: 2021-01-26 14:42
 * @desc: 基于虚拟单项链表实现的 公平锁
 **/
public class CLHLock implements Lock {

    private final ThreadLocal<CLHLock.Node> prev;
    private final ThreadLocal<CLHLock.Node> node;
    private final AtomicReference<Node> tail = new AtomicReference<>(new CLHLock.Node());


    public CLHLock() {
        this.prev = ThreadLocal.withInitial(() -> null);
        this.node = ThreadLocal.withInitial(CLHLock.Node::new);
    }


    private static class Node {

        private volatile boolean locked;
    }

    @Override
    public void lock() {

        // 不同的线程可以拿到不同的Node对象
        final Node node = this.node.get();
        // 设置为锁定状态
        node.locked = true;
        // 加入单项链表中
        Node pre_node = tail.getAndSet(node);
        // prev 指向 pre_node
        this.prev.set(pre_node);
        // 自旋 直到前一个请求节点释放锁
        while(pre_node.locked);

    }

    @Override
    public void unlock() {

        final Node node = this.node.get();

        node.locked = false;

        this.node.set(this.prev.get());

    }


    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }


}
