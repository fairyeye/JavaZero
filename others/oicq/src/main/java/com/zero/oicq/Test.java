package com.zero.oicq;

import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, String> map1 = new HashMap<>();
        map1.put("error", "123456");

        Map<String, String> map2 = new HashMap<>();

        map2.putAll(map1);
        System.out.println(map2);
    }
}
