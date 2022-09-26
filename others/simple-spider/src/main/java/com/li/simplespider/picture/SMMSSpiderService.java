package com.li.simplespider.picture;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpException;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/8/18 20:13
 */
@Service
public class SMMSSpiderService {

    private static final String pattern = "https://i.loli.net/.*.png";

    private static final Logger logger = LoggerFactory.getLogger(SMMSSpiderService.class);

    public static void main(String[] args) {
        SMMSSpiderService _this = new SMMSSpiderService();
        _this.doDownload("https://sm.ms/home/picture?page=1");
    }

    public void doDownload(String uri) {
        List<String> picUris = getAllPicUris(uri);
    }

    private List<String> getAllPicUris(String uri) {
        List<String> picUris = new ArrayList<>();
        // 解析主页
        Connection connect = Jsoup.connect(uri);
        connect.headers(initHeaders());
        try {
            Document document = connect.get();
            Element tablePictures = document.getElementById("table-picture");
            Elements hrefs = tablePictures.getElementsByAttribute("href");
            for (Element href : hrefs) {
                String imgUris = href.attr("href");
                String imgName = href.attr("data-caption");

                Pattern r = Pattern.compile(pattern);
                Matcher m = r.matcher(imgUris);
                if (m.matches()) {
                    picUris.add(imgUris);

                    HttpResponse response = HttpRequest.get(imgUris)
                            .addHeaders(initHeaders())
                            .header("Connection", "keep-alive")
                            .header("Accept-Encoding", "gzip, deflate, br")
                            .header("Accept", "*/*")
                            .timeout(-1).executeAsync();
                    if (!response.isOk()) {
                        throw new HttpException("Server response error with status code: [{}]", new Object[]{response.getStatus()});
                    }
                    long size = response.writeBody(FileUtil.file("C:/Users/10586/Downloads/ALiYun/SMMS/" + imgName), null);
                    logger.info("==========>imgUris is {}，size is {}", imgUris, size);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return picUris;
    }

    /**
     * 初始化请求头信息
     * @return
     */
    private Map<String, String> initHeaders() {
        Map<String, String> headers = new HashMap<>(4);
        headers.put("Connection", "keep-alive");
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("Accept", "*/*");
        headers.put("Cookie", getCookie());
        return headers;
    }

    /**
     * TODO 获取Cookie
     */
    private String getCookie() {
        return "PHPSESSID=rjod96mem8r92722bjv40snt1v; SMMSrememberme=18647%3A24d496ba1100710b2afdb46e3157b7ebd0d3b691; SM_FC=Ev2nGorh88wuTw6ZccixXYnIZ4pqMUPMEcpV2883yw%3D%3D";
    }
}
