package com.example.tacobel.controller;

import com.example.tacobel.entity.Taco;
import com.example.tacobel.entity.TacoOrder;
import com.example.tacobel.pojo.OrderInfo;
import com.example.tacobel.repository.TacoOrderRepository;
import com.example.tacobel.repository.UserRepository;
import com.example.tacobel.util.DeliveryCommunication;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/profile")
public class AccountController {

    private TacoOrderRepository tacoOrderRepository;

    private UserRepository userRepository;

    @Autowired
    public void setTacoOrderRepository(TacoOrderRepository tacoOrderRepository,
                                       UserRepository userRepository) {
        this.tacoOrderRepository = tacoOrderRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getProfile(Principal principal, Model model) throws JsonProcessingException {
        List<TacoOrder> orders
                = tacoOrderRepository.findTacoOrdersByUser(userRepository.findByUsername(principal.getName()));
        List<OrderInfo> info =
                DeliveryCommunication.send(orders.stream().map(TacoOrder::getId).collect(Collectors.toList()));
        model.addAttribute("orders", info);
        return "profile";
    }
}
