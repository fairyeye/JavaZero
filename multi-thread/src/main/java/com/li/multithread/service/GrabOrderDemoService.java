package com.li.multithread.service;

import java.util.Map;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2021/8/18 14:41
 */

public interface GrabOrderDemoService {
    void grabOrder(Map<String, String> map, String id) throws InterruptedException;
}
