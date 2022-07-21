package com.example.tacobel.controller;

import com.example.tacobel.entity.User;
import com.example.tacobel.exc.UserAlreadyExists;
import com.example.tacobel.repository.UserRepository;
import com.example.tacobel.service.EmailService;
import com.example.tacobel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashSet;

@Controller
public class UserController {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    @GetMapping("/registration")
    public String returnRegistrationPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String createUser(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String firstname,
                             @RequestParam String lastname,
                             @RequestParam String email) throws UserAlreadyExists {
        if (userService.findByUsername(username) != null) {
            throw new UserAlreadyExists("User with this parameters already exists");
        }
        User user = new User(username, passwordEncoder.encode(password), firstname, lastname, email, new HashSet<>());
        userService.saveUser(user);
        emailService.sendEmail(user.getEmail(), user.getName());
        return "redirect:/login";
    }


}
