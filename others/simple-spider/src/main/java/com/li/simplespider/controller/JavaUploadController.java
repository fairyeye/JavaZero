package com.li.simplespider.controller;

import com.alibaba.fastjson.JSONObject;
import com.li.simplespider.controller.dto.FileDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2022/6/13 16:21
 */
@RestController
public class JavaUploadController {

    @PostMapping(value = "/upload")
    public String upload(MultipartFile file, String uid, String token) {
        System.out.println("uid"+uid);
        System.out.println("token"+token);
        return JSONObject.toJSONString(new FileDTO("success"));
    }
}
