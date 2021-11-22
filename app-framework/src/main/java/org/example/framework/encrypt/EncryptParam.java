package org.example.framework.encrypt;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.example.framework.config.EncryptConfig;
import org.springframework.util.ObjectUtils;

/**
 * Created by myy on 2021/11/19.
 */
@Getter
@Setter
@Slf4j
public class EncryptParam {
    /**
     * 加密参数
     */
    private String encrypt;


    public EncryptParam() {
    }


    /**
     * 加密参数
     *
     * @param data
     */
    public EncryptParam(Object data) {
        String param = JSONObject.toJSONString(data);
        RSA rsa = new RSA(null, EncryptConfig.RAS_PUBLIC);
        byte[] encryptByte = rsa.encrypt(param, KeyType.PublicKey);
        this.encrypt = Base64.encodeBase64String(encryptByte);
    }

    /**
     * 得到解密的内容
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getParam(Class<T> clazz) {
        try {
            log.info("密文: {}", encrypt);
            // 解密
            RSA rsa = new RSA(EncryptConfig.RAS_PRIVATE, null);
            byte[] decrypt = rsa.decrypt(encrypt, KeyType.PrivateKey);
            String decryptSt = StrUtil.str(decrypt, CharsetUtil.CHARSET_UTF_8);
            if (ObjectUtils.isEmpty(decryptSt)) {
                throw new RuntimeException("解密后字符串为空");
            }
            log.info("解密后内容: {}", decryptSt);

            // 将字符串转换成实体
            T t = JSONObject.parseObject(decryptSt, clazz);
            if (ObjectUtils.isEmpty(t)) {
                throw new RuntimeException("JSON数据转换成对象失败");
            }
            return t;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("解密失败");
            throw new RuntimeException("解密失败");
        }
    }
}
