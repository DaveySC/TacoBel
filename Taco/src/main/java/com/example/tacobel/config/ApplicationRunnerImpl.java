package com.example.tacobel.config;

import com.example.tacobel.entity.Ingredient;
import com.example.tacobel.entity.Role;
import com.example.tacobel.entity.RoleName;
import com.example.tacobel.entity.User;
import com.example.tacobel.repository.IngredientRepository;
import com.example.tacobel.repository.RoleRepository;
import com.example.tacobel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.*;


@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private IngredientRepository repo;

    private RoleRepository roleRepository;

    private UserService userService;

    @Autowired
    public void setRepo(IngredientRepository repo,
                        RoleRepository roleRepository,
                        UserService userService)
    {
        this.repo = repo;
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.repo.saveAll(Arrays.asList(
                new Ingredient(null,"meetSouse", Ingredient.Type.SOUSE),
                new Ingredient(null,"cheeseSouse", Ingredient.Type.SOUSE),
                new Ingredient(null,"cow", Ingredient.Type.MEET),
                new Ingredient(null,"chicken", Ingredient.Type.MEET),
                new Ingredient(null,"pepper", Ingredient.Type.SPICE),
                new Ingredient(null,"salt", Ingredient.Type.SPICE),
                new Ingredient(null,"cheeseTortilla", Ingredient.Type.TORTILLA),
                new Ingredient(null,"wheatTortilla", Ingredient.Type.TORTILLA)
        ));

        this.roleRepository.saveAll(Arrays.asList(
                new Role(null, RoleName.USER_ROLE.name(), new HashSet<>()),
                new Role(null, RoleName.ADMIN_ROLE.name(), new HashSet<>())
        ));

        this.userService.saveUser(new User("TEST", "PASS", "TEST", "TEST", "email", new HashSet<>()));
    }
}
