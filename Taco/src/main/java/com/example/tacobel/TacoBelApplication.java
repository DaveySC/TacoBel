package com.example.tacobel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.util.Calendar;

@SpringBootApplication
@EnableWebSecurity
public class TacoBelApplication {

    public static void main (String[] args) {
        SpringApplication.run(TacoBelApplication.class, args);

    }

}
