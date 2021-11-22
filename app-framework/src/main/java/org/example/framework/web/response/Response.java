package org.example.framework.web.response;

import lombok.Data;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Created by myy on 2021/11/19.
 */
@Data
public class Response {

    private Boolean success;
    private String code;
    private String message;
    private Object data;
    private String traceId;
    private Boolean encrypt = false;


    public Response() {
    }

    public Response(Boolean success, String code, String message, Object data, String traceId) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
        this.traceId = traceId;
    }

    public static String traceID() {
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()))
                .getRequest();
        String traceId = request.getHeader("traceId");
        return ObjectUtils.isEmpty(traceId) ? "-1" : traceId;
    }
}
