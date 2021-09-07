package com.li.javazeromofish.service.impl;

import com.li.javazeromofish.service.MessageService;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/9/2 19:27
 */
@Component
public class MessageServiceImpl implements MessageService {

    private HashMap<LocalDate, String> holidayMap = new HashMap<LocalDate, String>();
    public static final String MESSAGE_1 = "距离%s还有%s天";

    @Override
    public void sendMessage() {
        StringBuilder message = new StringBuilder();
        LocalDate localDate = LocalDate.now();
        initHolidayInfo(localDate);
        initMessage(localDate, message);
        System.out.println(message);
    }

    /**
     * 初始化消息
     */
    private void initMessage(LocalDate localDate, StringBuilder message) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M月d日");
        message.append(localDate.format(dateTimeFormatter) + "\r\n");
        message.append("下午好，摸鱼人，工作再累，一定不要忘记摸鱼哦\r\n");
        message.append("有事没事起身去茶水间去厕所去廊道走走，别老在工位上坐着，钱是老板的，但命是自己的\r\n");
        calculateDays(localDate, message);
    }

    /**
     * 计算天数 并拼接返回消息
     * @param message
     */
    private void calculateDays(LocalDate localDate, StringBuilder message) {
        Set<LocalDate> localDates = holidayMap.keySet();
        List<LocalDate> dates = localDates.stream().sorted().collect(Collectors.toList());
        for (LocalDate date : dates) {
            message.append(String.format(MESSAGE_1, holidayMap.get(date), date.toEpochDay() - localDate.toEpochDay()));
            message.append("\r\n");
        }
    }

    /**
     * 初始化2021年假期信息
     * TODO 可以通过API获取节日信息 暂时没找到好用的API
     *
     * @return
     */
    private void initHolidayInfo(LocalDate localDate) {
        holidayMap.put(localDate.with(DayOfWeek.SUNDAY), "本周周末");
        holidayMap.put(LocalDate.of(2021, 9, 21), "中秋假期");
        holidayMap.put(LocalDate.of(2021, 10, 1), "国庆假期");
        holidayMap.put(LocalDate.of(2022, 1, 1), "元旦假期");
        holidayMap.put(LocalDate.of(2022, 1, 31), "除夕假期");
    }

    /**
     *
     * 9月2日
     * 下午好，摸鱼人，工作再累，一定不要忘记摸鱼哦
     * 有事没事起身去茶水间去厕所去廊道走走，别老在工位上坐着，钱是老板的，但命是自己的
     * 距离本周周末还有1天
     * 距离中秋假期还有17天
     * 距离国庆假期还有29天
     * 距离跨年假期还有120天
     * 距离春节假期还有151天
     *
     * @param args
     */
    public static void main(String[] args) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M月d日"); // 10月1日
//        System.out.println(LocalDate.of(2021, 10, 1).format(dateTimeFormatter));
//        System.out.println(String.format(MessageServiceImpl.MESSAGE_1, "本周", "3")); // 距离本周还有3天
//        System.out.println(LocalDate.now().with(DayOfWeek.SUNDAY)); // 2021-09-12
//        System.out.println(LocalDate.now());
//        System.out.println(Period.between(LocalDate.now(), LocalDate.now().with(DayOfWeek.SUNDAY)).getDays());
        MessageServiceImpl impl = new MessageServiceImpl();
        impl.sendMessage();
    }
}
