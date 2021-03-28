package com.labs.kubernetes.springcloudkubernetesconfigexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/welcome")
@RestController
public class WelcomeController {

    private static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @Value("${message: Welcome to Welcome Controller}")
    private String message;

    @GetMapping
    public String welcome() {
        logger.info("message: {}", message);
        return message;
    }

}
