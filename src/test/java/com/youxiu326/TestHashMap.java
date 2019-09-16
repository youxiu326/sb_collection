package com.youxiu326;

import com.youxiu326.collection.MyHashMap;
import com.youxiu326.inter.MyMap;

public class TestHashMap {


    public static void main(String[] args) {

        MyMap<String,String> myMap = new MyHashMap<>();
        myMap.put("1号", "1号值");// 0
        myMap.put("2号", "2号值");// 1
        myMap.put("3号", "3号值");// 2
        myMap.put("4号", "4号值");// 3
        myMap.put("6号", "6号值");// 4
        myMap.put("7号", "7号值");
        myMap.put("14号", "14号值");
        for (int i = 0; i < 35; i++) {
            myMap.put(i+"牛逼", i+"");
        }
        myMap.put("14号", "14号值的值的值");
        myMap.print();

    }

}