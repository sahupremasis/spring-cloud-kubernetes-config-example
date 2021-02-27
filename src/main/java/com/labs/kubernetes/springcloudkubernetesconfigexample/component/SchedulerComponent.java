package com.labs.kubernetes.springcloudkubernetesconfigexample.component;

import com.labs.kubernetes.springcloudkubernetesconfigexample.config.WelcomeConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerComponent {

    private static final Logger logger = LoggerFactory.getLogger(SchedulerComponent.class);


    @Autowired
    WelcomeConfig welcomeConfig;

    @Scheduled(fixedDelay = 3000)
    public void scheduled() {
        logger.info("config message: {}", welcomeConfig.getMessage());
    }


}
