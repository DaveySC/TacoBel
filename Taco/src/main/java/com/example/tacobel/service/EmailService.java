package com.example.tacobel.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService {
    private PasswordEncoder encoder;

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public JavaMailSender emailSender;

    String url = "localhost:8080/confirm?toke=" ;

    private static final String preparedMessage =
            "Hey, %s!\n Please, confirm your email by link bellow %S:\n ";

    @Autowired
    public void setEmailSender(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendEmail(String email, String name) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Please confirm your email!");
        message.setText(String.format(preparedMessage, name, generateUniqUrl(email)));
        this.emailSender.send(message);
    }

    private String generateUniqUrl(String email) {
        return  url + encoder.encode(email);
    }

}
