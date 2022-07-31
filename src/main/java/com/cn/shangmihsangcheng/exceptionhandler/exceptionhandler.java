package com.cn.shangmihsangcheng.exceptionhandler;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 进行全局异常处理
 * */
@ControllerAdvice
public class exceptionhandler {

    @ExceptionHandler(value = AuthorizationException.class)
    public ModelAndView doException(Exception e){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("msg","您还未登录，请先登录");
        modelAndView.setViewName("/index/error.jsp");
        return modelAndView;
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ModelAndView loginException(Exception e){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("msg",e.getMessage());
        modelAndView.setViewName("/index/login.jsp");
        return modelAndView;
    }

}
