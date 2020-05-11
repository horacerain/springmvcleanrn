package com.horace.springmvc.crud.test;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ModelAndView handleArithmeticException(Exception ex){
        System.out.println("global check ---->出异常了："+ex);
//        map.put("exception",ex);
        ModelAndView modelAndView = new ModelAndView("error");
        modelAndView.addObject("exception",ex);
        StackTraceElement[] stack = ex.getStackTrace();
        System.out.println(stack.length);
        System.out.println(stack.toString());
        modelAndView.addObject("stack",stack);

        return modelAndView;
    }
}
