package com.li.javazeromofish.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.li.javazeromofish.client.HitokotoClient;
import com.li.javazeromofish.service.MessageService;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/9/2 19:27
 */
@Component
public class MessageServiceImpl implements MessageService {

    @Autowired
    private HitokotoClient hitokotoClient;

    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private HashMap<LocalDate, String> holidayMap = new HashMap<LocalDate, String>();
    public static final String MESSAGE_1 = "距离%s还有%s天";

    @Override
    public void sendMessage() {
        StringBuilder message = new StringBuilder();
        LocalDate localDate = LocalDate.now();
        initHolidayInfo(localDate);
        initMessage(localDate, message);
        logger.info(message.toString());

        HashMap<String, String> content = new HashMap<>(2);
        content.put("title", "摸鱼通知");
        content.put("desp", message.toString());

        String params = JSONObject.toJSONString(content);
        System.out.println(params);
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder();
        LocalDate localDate = LocalDate.now();
        initHolidayInfo(localDate);
        initMessage(localDate, message);

        return message.toString();
    }

    @Override
    public String initMessage(String content) {
        if (StringUtils.contains(content, "一言")) {
            return hitokotoClient.getString('i');
        }
        return this.getMessage();
    }

    private void sendTurbo(StringBuilder message) throws IOException {

        HashMap<String, String> content = new HashMap<>(2);
        content.put("title", "摸鱼通知");
        content.put("desp", message.toString());

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url("https://sctapi.ftqq.com/SCT68441T5mf3LEBVkFUa9jfcLqHPFC5Y.send?title=通知&desp=" + message.toString())
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
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
            if (date.toEpochDay() - localDate.toEpochDay() <= 0) {
                continue;
            }
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
        holidayMap.put(LocalDate.of(2022, 9, 21), "中秋假期");
        holidayMap.put(LocalDate.of(2022, 10, 1), "国庆假期");
        holidayMap.put(LocalDate.of(2023, 1, 1), "元旦假期");
        holidayMap.put(LocalDate.of(2023, 1, 31), "除夕假期");
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
//        MessageServiceImpl impl = new MessageServiceImpl();
//        impl.sendMessage();
//        System.out.println(LocalDate.of(2021, 5, 1).toEpochDay() - LocalDate.of(2021, 10, 10).toEpochDay());
    }
}
