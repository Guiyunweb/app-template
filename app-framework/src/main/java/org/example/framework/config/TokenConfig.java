package org.example.framework.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * JWT 配置
 * Created by myy on 2021/8/26.
 */

@ConfigurationProperties(prefix = "auth.token")
public class TokenConfig {


    public static String header;

    public static String secret;

    public static Long expireTime;

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        TokenConfig.header = header;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        TokenConfig.secret = secret;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        TokenConfig.expireTime = expireTime;
    }
}
