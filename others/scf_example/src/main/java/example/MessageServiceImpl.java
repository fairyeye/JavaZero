package example;

import okhttp3.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.io.IOException;


/**
 * @author ba
 * @time 2021/9/2 19:27
 */
public class MessageServiceImpl{


    private HashMap<LocalDate, String> holidayMap = new HashMap<LocalDate, String>();
    public static final String MESSAGE_1 = "距离%s还有%s天";

    public void sendMessage() {
        StringBuilder message = new StringBuilder();
        LocalDate localDate = LocalDate.now();
        initHolidayInfo(localDate);
        initMessage(localDate, message);

        try {
            sendTurbo(message);
        } catch (IOException e) {
            System.out.println("发送消息失败，失败原因" + e.getMessage());
        }

        System.out.println(message);
    }

    private void sendTurbo(StringBuilder message) throws IOException {

        HashMap<String, String> content = new HashMap<>(2);
        content.put("title", "摸鱼通知");
        content.put("desp", message.toString());

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                // 填server酱的sendKey
                .url("https://sctapi.ftqq.com/xxxxxxxxxxxxxxxxxxxxxxxxx.send?title=摸鱼通知&desp=" + message.toString())
                .method("POST", body)
                .build();
        Response response = client.newCall(request).execute();
    }

    /**
     * 初始化消息
     */
    private void initMessage(LocalDate localDate, StringBuilder message) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("M月d日");
        message.append(localDate.format(dateTimeFormatter) + "。\n\n");
        message.append("下午好，摸鱼人，工作再累，一定不要忘记摸鱼哦。\n\n");
        message.append("有事没事起身去茶水间去厕所去廊道走走，别老在工位上坐着，钱是老板的，但命是自己的。\n\n");
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
            message.append("。\n\n");
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
}
