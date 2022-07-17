package com.example.tacobel.controller;

import com.example.tacobel.entity.TacoOrder;
import com.example.tacobel.repository.TacoOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/order")
public class OrderController {

    private TacoOrderRepository repo;

    @Autowired
    public void setRepo(TacoOrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String getOrderPage(@SessionAttribute("tacoOrder")TacoOrder tacoOrder, Model model)  {
        model.addAttribute("tacoOrder", tacoOrder);
        return "order";
    }

    @PostMapping
    public String getOrder(@ModelAttribute TacoOrder tacoOrder, SessionStatus status) {
        this.repo.save(tacoOrder);
        status.setComplete();
        return "redirect:/";
    }
}
