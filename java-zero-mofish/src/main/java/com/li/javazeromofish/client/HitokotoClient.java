package com.li.javazeromofish.client;

import com.dtflys.forest.annotation.Get;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/12/24 16:41
 */
public interface HitokotoClient {

    @Get("https://v1.hitokoto.cn/?encode=text&c={0}")
    String getString(char c);
}
