package org.example.framework.web.response;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.framework.encrypt.EncryptParam;

/**
 * Created by myy on 2021/11/19.
 */
@Slf4j
public class SuccessResponse extends Response{

    public SuccessResponse() {
        super(Boolean.TRUE, "200", "请求成功", (Object) null, traceID());
    }

    public SuccessResponse(Object object) {
        super(Boolean.TRUE, "200", "请求成功", object, traceID());
    }

    public SuccessResponse(String code, String message, Object object) {
        super(Boolean.TRUE, code, message, object, traceID());
    }

    /**
     * 参数加密
     */
    public SuccessResponse encrypt() {

        log.info("返回参数: {}",JSONObject.toJSONString(getData()));
        setEncrypt(true);
        setData(new EncryptParam(getData()));
        return this;
    }

}
