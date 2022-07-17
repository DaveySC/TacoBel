package com.example.integrationservice.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    private String name;

    private Type type;

    public enum Type{SOUSE, MEET, SPICE, TORTILLA}

}
