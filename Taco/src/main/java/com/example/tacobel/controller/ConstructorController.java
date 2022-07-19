package com.example.tacobel.controller;

import com.example.tacobel.entity.Ingredient;
import com.example.tacobel.entity.Taco;
import com.example.tacobel.entity.TacoOrder;
import com.example.tacobel.repository.IngredientRepository;
import com.example.tacobel.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.tacobel.entity.Ingredient.*;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class ConstructorController {

    private IngredientRepository repo;

    private TacoRepository tacoRepository;

    @Autowired
    public void setTacoRepository(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @Autowired
    public void setRepo(IngredientRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public String getConstructor(Model model) {
        for (Type type : Type.values()) {
            model.addAttribute(type.name().toLowerCase(), filter(repo.findAll(), type));
        }

        return "constructor";
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder tacoOrder() {
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    private Iterable<Ingredient> filter(Iterable<Ingredient> list,
                                        Type type) {
        return StreamSupport.stream(list.spliterator(), false)
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping()
    public String processingTaco(@ModelAttribute Taco taco, @SessionAttribute("tacoOrder") TacoOrder tacoOrder) {
        tacoOrder.addTaco(tacoRepository.save(taco));

        return "redirect:/order";
    }
}
