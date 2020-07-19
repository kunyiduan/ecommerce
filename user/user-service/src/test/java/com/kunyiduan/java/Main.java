package com.kunyiduan.java;

public class Main {

    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0ZWxlcGhvbmUiOiIxNTkyNTEwODU3NSIsImlkIjoiMTI4MTg2NzY5ODUxNTA3OTE3MCIsImV4cCI6MTU5NjAzNTgxMSwidmVyc2lvbiI6IjE1OTQ3Mzk4MTE5ODkifQ.O1VKLajtk9w3jrECYRxBaHdcKWgT7CpJOyN1qr82A4E";
        String[] split = token.split("\\.");
        for (String s : split){
            System.out.println(s);
        }
    }
}
