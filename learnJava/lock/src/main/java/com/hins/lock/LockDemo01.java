package com.hins.lock;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: hins
 * @created: 2020-12-30 14:53
 * @desc:
 **/
public class LockDemo01 {

    private Integer score = 60;

    private Map<String,String> map = new HashMap<>();

    public LockDemo01(){
        map.put("name", "Raymond");
    }

    public Integer changeScore(){

        Integer tempScore = score;
        tempScore++;

        return tempScore;
    }

    public Map changeMap(){

        Map tempMap = map;
        tempMap.put("name", "Wyman");

        return tempMap;
    }

    public Integer getScore() {
        return score;
    }


    public static void main(String[] args) {
        
        LockDemo01 lockDemo01 = new LockDemo01();
        Map map = lockDemo01.changeMap();
        System.out.println("返回修改的方法局部变量: " + map);
        System.out.println("返回对象的成员变量: "+ lockDemo01.map);

    }
}
