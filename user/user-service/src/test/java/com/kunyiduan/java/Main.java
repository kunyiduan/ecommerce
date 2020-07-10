package com.kunyiduan.java;

import java.sql.Timestamp;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Timestamp date = new Timestamp(System.currentTimeMillis());
        System.out.println(date);
        Date d = new Date();
        System.out.println(d);
    }
}
