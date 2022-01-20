package com.li.javazeroredis.utils;

import com.li.javazeroredis.instance.SecureRandomInstance;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author huapeng.zhang@going-link.com
 * @time 2022/1/7 14:18
 */
public class VerifyCodeUtil {

    /**
     * 随机生成验证码
     *
     * @return 验证码
     */
    public static String initVerifyCode() {

        StringBuffer verifyCode = new StringBuffer();

        SecureRandom secureRandom = SecureRandomInstance.getInstance();

        for (int i = 0; i < 6; i++) {
            verifyCode.append(secureRandom.nextInt(10));
        }
        return verifyCode.toString();
    }
}
