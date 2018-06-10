package com.yidong.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('USER')")//springsecurity要求有USER权限才可访问此contoller
public class TestContoller {

    @RequestMapping(value="/test",method = RequestMethod.GET)
    public String test(){
        return "HelloWorld!";

    }
}
