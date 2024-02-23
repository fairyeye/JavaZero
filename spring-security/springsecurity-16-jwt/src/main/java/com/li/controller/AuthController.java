package com.li.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/has_tea_auth")
    @PreAuthorize("hasAuthority('teacher:query')")
    public String hasTeaAuth(){
        return "hasAuthority('teacher:query')";
    }
    @GetMapping("/has_stu_auth")
    @PreAuthorize("hasAuthority('student:query')")
    public String hasStuAuth(){
        return "hasAuthority('student:query')";
    }
    @GetMapping("/auth")
    public String auth(){
        return "auth";
    }
}
