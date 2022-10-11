package com.java.zero;

import com.alibaba.fastjson.JSONObject;
import com.java.zero.entity.School;
import com.java.zero.mapper.SchoolMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class JavaZeroMybatisApplicationTests {

    @Resource
    private SchoolMapper schoolMapper;
    @Test
    void contextLoads() {

        List<School> schools = schoolMapper.query();

        System.out.println(JSONObject.toJSONString(schools));

    }

}
