package com.li.javazeromofish.controller;

import java.time.LocalDate;

/**
 * @author huapeng.zhang@hand-china.com
 * @date 2022/01/03 9:15 下午
 */
public class DemoController {
    public static void main(String[] args) {
        int year = 2019;
        int month = 8;
        int day = 31;
        LocalDate localDate = LocalDate.of(year, month, day);
        System.out.println(localDate.getDayOfWeek().toString().toLowerCase());
    }
}
