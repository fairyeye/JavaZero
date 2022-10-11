package com.java.zero;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 */
public class App {

    public static final Map<String, String> map = new HashMap<String, String>(4);

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public static void order(String str) {
        if (str == "A") {
            map.put(str, "AA");
        }
        if (str == "B") {
            map.put(str, "BB");
        }
        if (str == "C") {
            map.put(str, "CC");
        }
        if (str == "D") {
            map.put(str, "DD");
        }
    }

    public static void display() {
        System.out.println(JSONObject.toJSONString(map));
    }
}
