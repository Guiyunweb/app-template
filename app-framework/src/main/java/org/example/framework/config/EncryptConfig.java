package org.example.framework.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by myy on 2021/11/19.
 */
@Configuration
@Slf4j
public class EncryptConfig {

    /**
     * 密钥钥
     */
    public static String RAS_PRIVATE;
    /**
     * 公钥
     */
    public static String RAS_PUBLIC;

    @Bean
    public void getPublicKey() {
        log.info("开始 读取RAS加密公钥");
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ras/rsa_public_key.pem");
        try {
            assert inputStream != null;
            RAS_PUBLIC = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            if (ObjectUtils.isEmpty(RAS_PUBLIC)) {
                log.info("读取RAS加密密钥 未空");
            } else {
                log.info("读取RAS加密密钥 成功");
            }
        } catch (IOException e) {
            log.error("读取RAS加密公钥失败");
            e.printStackTrace();
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Bean
    public void getPrivateKey() {
        log.info("开始 读取RAS加密密钥");
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("ras/rsa_private_key.pem");
        try {
            assert inputStream != null;
            RAS_PRIVATE = IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            if (ObjectUtils.isEmpty(RAS_PRIVATE)) {
                log.info("读取RAS加密密钥 为空");
            } else {
                log.info("读取RAS加密密钥 成功");
            }
        } catch (IOException e) {
            log.error("读取RAS加密密钥失败");
            e.printStackTrace();
        } finally {
            try {
                assert inputStream != null;
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
