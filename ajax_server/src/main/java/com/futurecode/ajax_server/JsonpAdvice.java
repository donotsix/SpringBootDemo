package com.futurecode.ajax_server;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractJsonpResponseBodyAdvice;

/**
 * @author :lvweihao@outlook.com
 * @description:
 * @date :create in 21:23 18.5.21
 * @modify by :
 */
@ControllerAdvice
public class JsonpAdvice extends AbstractJsonpResponseBodyAdvice {

    public JsonpAdvice() {
        super("callback2");
    }
}
