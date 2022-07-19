package com.example.tacobel.rest;

import com.example.tacobel.entity.Ingredient;
import com.example.tacobel.entity.TacoOrder;
import com.example.tacobel.repository.IngredientRepository;
import com.example.tacobel.repository.TacoOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/tacos_orders", produces="application/json")
public class TacoOrderRestController {

    private TacoOrderRepository repo;

    @Autowired
    public TacoOrderRestController(TacoOrderRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<TacoOrder> allTacosOrders() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder saveTacoOrder(@RequestBody TacoOrder order) {
        return repo.save(order);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") String orderId) {
        repo.deleteById(Long.parseLong(orderId));
    }

}
