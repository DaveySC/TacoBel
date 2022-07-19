package com.example.tacobel.rest;

import com.example.tacobel.entity.Ingredient;
import com.example.tacobel.entity.Taco;
import com.example.tacobel.repository.IngredientRepository;
import com.example.tacobel.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/api/tacos", produces="application/json")
public class TacoRestController {

    private TacoRepository repo;

    @Autowired
    public TacoRestController(TacoRepository repo) {
        this.repo = repo;
    }

    @GetMapping
    public Iterable<Taco> allTacos() {
        return repo.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Taco saveTaco(@RequestBody Taco taco) {
        return repo.save(taco);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaco(@PathVariable("id") String tacoId) {
        repo.deleteById(Long.parseLong(tacoId));
    }

}
