package com.kit.calendar;

import com.google.gson.Gson;

import java.util.HashMap;

public class Test {
    public static void main(String[] args){
        HashMap<String,String> info = new HashMap<>();
        info.put("H0202","fdf");
        Gson gson = new Gson();System.out.println("输入的日志为："+gson.toJson(info));
    }
}
