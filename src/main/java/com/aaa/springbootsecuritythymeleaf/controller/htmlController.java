package com.aaa.springbootsecuritythymeleaf.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 淮南King
 * @date 2020-08-04
 */
@RestController
public class htmlController {
    @RequestMapping("/index")
    public ModelAndView index(){
        return new ModelAndView("/index");
    }

    @RequestMapping("/test")
    public Object test(){
        return "test 此请求无需权限";
    }

    /**
     * 自定义登录页面
     * @param error 错误信息显示标识
     * @return
     *
     */
    @GetMapping("/login")
    public ModelAndView login(String error){
        ModelAndView modelAndView = new ModelAndView("/login");
        modelAndView.addObject("error", error);
        return modelAndView;
    }
}
