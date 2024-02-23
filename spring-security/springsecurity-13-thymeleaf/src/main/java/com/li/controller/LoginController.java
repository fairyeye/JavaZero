package com.li.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
    /**
     * 跳转到登陆页面
     */
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

}
