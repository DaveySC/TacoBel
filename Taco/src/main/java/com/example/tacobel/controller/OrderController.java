package com.example.tacobel.controller;

import com.example.tacobel.entity.TacoOrder;
import com.example.tacobel.entity.User;
import com.example.tacobel.repository.TacoOrderRepository;
import com.example.tacobel.repository.UserRepository;
import com.example.tacobel.util.DeliveryCommunication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.security.Principal;

@Controller
@RequestMapping("/order")
@SessionAttributes("tacoOrder")
public class OrderController {

    private TacoOrderRepository repo;

    private UserRepository userRepository;

    @Autowired
    public void setRepo(TacoOrderRepository repo,
                        UserRepository userRepository) {
        this.repo = repo;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String getOrderPage(@SessionAttribute("tacoOrder")TacoOrder tacoOrder, Model model)  {
        model.addAttribute("tacoOrder", tacoOrder);
        return "order";
    }

    @PostMapping
    public String getOrder(TacoOrder tacoOrder, SessionStatus status, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        tacoOrder.setUser(user);
        DeliveryCommunication.send(repo.save(tacoOrder));
        status.setComplete();
        return "redirect:/";
    }
}
