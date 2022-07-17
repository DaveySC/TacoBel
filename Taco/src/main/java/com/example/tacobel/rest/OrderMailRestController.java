package com.example.tacobel.rest;

import com.example.tacobel.pojo.EmailOrder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/api/orders/fromEmail", produces="application/json")
public class OrderMailRestController {

    @PostMapping
    public void getOrderFromEmail(EmailOrder emailOrder) {
        System.out.println(emailOrder);
    }

}
