package com.futurecode.springbooterror.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class BizExceptionHandler {

    @ExceptionHandler({RuntimeException.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processRuntimeException(RuntimeException runtimeException) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("futurecode_runtimeexception" , runtimeException);
        modelAndView.setViewName("error/500");
        return modelAndView;
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView processRuntimeException(Exception exception) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("futurecode_exception" , exception);
        modelAndView.setViewName("error/501");
        return modelAndView;
    }
}
