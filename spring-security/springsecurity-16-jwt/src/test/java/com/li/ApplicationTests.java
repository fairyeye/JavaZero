package com.li;

import com.li.dao.SysMenuDao;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    private SysMenuDao sysMenuDao;
    @Test
    void queryPermissionByUserId() {
        List<String> menuList = sysMenuDao.queryPermissionByUserId(1);
        assertTrue(!menuList.isEmpty());
    }


}
