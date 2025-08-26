package com.example.demo.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
    @GetMapping("/")
    public String getMethodName() {
        return "/client/homepage/show";
    }

}
