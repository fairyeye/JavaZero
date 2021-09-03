package com.li.simplespider.controller;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/8/25 14:11
 */
public class Demo {

    public static void main(String[] args) {
        for (int i = 0; i <= 60; i++) {
            int random = (int) (Math.random() * 60);
            System.out.println("第" + i + "次：\t" + random);
        }
    }
}
