package com.li.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class JwtUtils {
    //算法密钥
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    /**
     * 创建jwt
     *
     * @param userInfo 用户信息
     * @param authList 用户权限列表
     * @return 返回jwt（JSON WEB TOKEN）
     */
    public String createToken(String userInfo, List<String> authList) {
        //创建时间
        Date currentTime = new Date();
        //过期时间，5分钟后过期
        Date expireTime = new Date(currentTime.getTime() + (1000 * 60 * 5));
        //jwt 的header信息
        Map<String, Object> headerClaims = new HashMap<>();
        headerClaims.put("type", "JWT");
        headerClaims.put("alg", "HS256");
        //创建jwt
        return JWT.create()
                .withHeader(headerClaims) // 头部
                .withIssuedAt(currentTime) //已注册声明：签发日期，发行日期
                .withExpiresAt(expireTime) //已注册声明 过期时间
                .withIssuer("thomas")  //已注册声明，签发人
                .withClaim("userInfo", userInfo) //私有声明，可以自己定义
                .withClaim("authList", authList) //私有声明，可以自定义
                .sign(Algorithm.HMAC256(jwtSecretKey)); // 签名，使用HS256算法签名，并使用密钥
//        HS256是一种对称算法，这意味着只有一个密钥，在双方之间共享。 使用相同的密钥生成签名并对其进行验证。 应特别注意钥匙是否保密。
    }

    /**
     * 验证jwt的签名，简称验签
     *
     * @param token 需要验签的jwt
     * @return 验签结果
     */
    public boolean verifyToken(String token) {
        //获取验签类对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecretKey)).build();
        try {
            //验签，如果不报错，则说明jwt是合法的，而且也没有过期
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            //如果报错说明jwt 为非法的，或者已过期（已过期也属于非法的）
            log.error("验签失败：{}", token);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 获取用户id
     *
     * @param token jwt
     * @return 用户id
     */
    public String getUserInfo(String token) {
        //创建jwt验签对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecretKey)).build();
        try {
            //验签
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            //获取payload中userInfo的值，并返回
            return decodedJWT.getClaim("userInfo").asString();
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取用户权限
     *
     * @param token
     * @return
     */
    public List<String> getUserAuth(String token) {
        //创建jwt验签对象
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(jwtSecretKey)).build();
        try {
            //验签
            DecodedJWT decodedJWT = jwtVerifier.verify(token);
            //获取payload中的自定义数据authList（权限列表），并返回
            return decodedJWT.getClaim("authList").asList(String.class);
        } catch (JWTVerificationException e) {
            e.printStackTrace();
        }
        return null;
    }

}
