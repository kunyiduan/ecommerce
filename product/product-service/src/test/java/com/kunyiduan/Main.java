package com.kunyiduan;

import java.math.BigDecimal;
import java.text.Collator;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
//        Map<String, List<Integer>> result = new TreeMap<>();
//        result.put("二号机",Arrays.asList(2,8));
//        result.put("四号机",Arrays.asList(4,9));
//        result.put("一号机",Arrays.asList(1,4));
//        result.put("三号机",Arrays.asList(3,5));
//        System.out.println(result);
//
//        List<String> collect = result.keySet().stream().sorted(Collator.getInstance(Locale.CHINA)).collect(Collectors.toList());
//        System.out.println(collect);
//        Map<String, List<Integer>> sortedResult = new TreeMap<>();
//        collect.forEach(x->{
//            sortedResult.put(x,result.get(x));
//        });
//
//        System.out.println(sortedResult);

//        BigDecimal value = new BigDecimal("10.00");
//        BigDecimal value2 = new BigDecimal(value.stripTrailingZeros().toPlainString());
//        System.out.println(value2);

        Map map = new ConcurrentHashMap();
    }
}
