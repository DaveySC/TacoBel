package com.example.tacobel.controller;

import com.example.tacobel.entity.User;
import com.example.tacobel.exc.UserAlreadyExists;
import com.example.tacobel.repository.UserRepository;
import com.example.tacobel.service.EmailService;
import com.example.tacobel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
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
        User user = new User(username, password, firstname, lastname, email, new HashSet<>());
        emailService.sendEmail(user.getEmail(), user.getName());
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/confirm")
    @ResponseBody
    public String activateAccount(@NotNull @RequestParam(defaultValue = "empty") String token) {
        User user = userService.findByEmail(token);
        if (user == null) return "USER NOT FOUND";
        user.setStatus(true);
        userService.saveUser(user);
        return "YOUR ACCOUNT ACTIVATED";
    }


}
