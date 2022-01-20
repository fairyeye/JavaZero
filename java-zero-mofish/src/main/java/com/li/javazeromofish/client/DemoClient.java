package com.li.javazeromofish.client;

import com.dtflys.forest.annotation.Get;
import com.dtflys.forest.annotation.Request;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/12/11 14:47
 */
public interface DemoClient {

    @Get("http://localhost:8080/hello")
    String hello();

}
