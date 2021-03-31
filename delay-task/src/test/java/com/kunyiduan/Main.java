package com.kunyiduan;

import com.kunyiduan.response.Result;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    String str = new String("good");
    char[] ch = {'a','b','c'};

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        Main main = new Main();
//        main.change(main.str, main.ch);
//        System.out.println(main.str + " and ");
//        System.out.println(main.ch);

//        Result result = new Result().suc();
//        Field[] declaredFields = result.getClass().getDeclaredFields();
//        for(Field field : declaredFields){
//            field.setAccessible(true);
//            System.out.println(field.getName());
//        }
//        System.out.println();
//
//        Field desc = result.getClass().getDeclaredField("desc");
//        desc.setAccessible(true);
//        Object o = desc.get(result);
//        System.out.println(desc.getName());
//        System.out.println(desc.getType());
//        System.out.println("--------"+o);
//        System.out.println(desc);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        System.out.println(LocalDate.now().format(formatter));
    }

    public void change(String str,char[] ch){
        str = "test ok";
        ch[0]= 'g';
    }
}
