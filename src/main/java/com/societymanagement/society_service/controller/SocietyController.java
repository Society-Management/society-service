package com.societymanagement.society_service.controller;

import com.societymanagement.society_service.utils.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class SocietyController {

    @GetMapping
    public String getUser(){
        return "Admin"+CurrentUser.getUserId();
    }
}

