package com.example.integrationservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Taco {

    public Taco(String name) {
        this.name = name;
    }

    String name;

    List<String> ingredients = new ArrayList<>();

}
