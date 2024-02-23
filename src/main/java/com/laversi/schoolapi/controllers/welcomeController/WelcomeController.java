package com.laversi.schoolapi.controllers.welcomeController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "<div style=\"text-align: center; margin-top: 50px;\">" +
                "<h1>Welcome to API</h1>" +
                "</div>";
    }

}