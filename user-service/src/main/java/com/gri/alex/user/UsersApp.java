package com.gri.alex.user;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@RefreshScope
@RestController
public class UsersApp {

    @Value("${app.greet.msg}")
    String message;

    public static void main(String[] args) {
        SpringApplication.run(UsersApp.class, args);
    }

    @RequestMapping("/")
    public String greet() {
        return message;
    }

}
