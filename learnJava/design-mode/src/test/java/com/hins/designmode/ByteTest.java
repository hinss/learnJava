package com.hins.designmode;

import org.junit.Test;

/**
 * @author: hins
 * @created: 2020-12-18 16:36
 * @desc: hash 求 下标位置的测试类
 **/
public class ByteTest {

    @Test
    public void test01(){

        int arrLength = 8;
        System.out.println("数组的长度:" + arrLength);
        String str1 = "jlkk";
        // 获得字符串的hashCode
        int hashCode = str1.hashCode();
        System.out.println(str1 + " hashCode: " + hashCode);
        System.out.println(str1 + " 二进制数组: " + Integer.toBinaryString(hashCode));
        System.out.println("数组的长度 二进制数组: " + Integer.toBinaryString(arrLength - 1));

        System.out.println("=====进行求余运算======");
        System.out.println("结果: " + (hashCode & (arrLength-1)));
        System.out.println("结果二进制数组: " + Integer.toBinaryString(hashCode & (arrLength-1)));

    }


}
