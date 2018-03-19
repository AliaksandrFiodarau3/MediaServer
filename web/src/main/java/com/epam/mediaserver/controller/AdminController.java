package com.epam.mediaserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
@EnableWebMvc
@RequestMapping("admin")
public class AdminController {

    @RequestMapping(method = RequestMethod.GET)
    public String homePage() {
        return "admin";
    }


}
