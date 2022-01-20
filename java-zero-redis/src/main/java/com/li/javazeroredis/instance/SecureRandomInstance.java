package com.li.javazeroredis.instance;

import java.security.SecureRandom;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2022/1/7 14:35
 */
public class SecureRandomInstance {

    private static volatile SecureRandom secureRandom = null;

    private SecureRandomInstance() {
    }

    public static SecureRandom getInstance() {
        if (secureRandom == null) {
            // DCL双端加锁 + volatile
            synchronized (SecureRandom.class) {
                if (secureRandom == null) {
                    secureRandom = new SecureRandom();
                }
            }
        }
        return secureRandom;
    }
}
