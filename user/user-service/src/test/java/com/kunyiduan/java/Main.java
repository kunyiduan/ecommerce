package com.kunyiduan.java;


import java.text.Collator;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("机组1", "a");
        map.put("机组4", "d");
        map.put("机组3", "c");
        map.put("机组2", "b");

        System.out.println("befor"+map);

        testChange(map);

        System.out.println("after"+map);

    }

    public static void testChange(Map<String,String> map){
        if(map.keySet().contains("机组4")){
            map.put("机组4","test");
        }
    }
}
