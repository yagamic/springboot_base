package com.yagamic.base.appliaction.web;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.lang.model.UnknownEntityException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@Slf4j
@ControllerAdvice(basePackages = {"com.yagamic.base.application"})
public class ExceptionHandlingAdvice {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UnknownEntityException.class)
    public void unknownEntityException() {}

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(IllegalAccessError.class)
    public @ResponseBody
    String illegalAccessError(IllegalAccessError e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public @ResponseBody
    String illegalArgumentException(IllegalArgumentException e) {
        return e.getMessage();
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(IllegalStateException.class)
    public @ResponseBody
    String illegalStateException(IllegalStateException e) {
        return e.getMessage();
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public @ResponseBody
    String serverError(HttpServletRequest request, HttpServletResponse response, Exception e) {
        log.debug("server 500 : URL:{},detail:{}",request.getRequestURI(),ExceptionUtils.getFullStackTrace(e));
        return "网络错误";
    }

}
