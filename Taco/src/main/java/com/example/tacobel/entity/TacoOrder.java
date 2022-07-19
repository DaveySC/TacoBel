package com.example.tacobel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TacoOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Min(2)
    @Max(15)
    private String city;

    @NotEmpty
    @Min(2)
    @Max(15)
    private String street;

    @NotEmpty
    @Min(2)
    @Max(15)
    private String house;

    @NotEmpty
    private String cardNumber;

    @NotEmpty
    private String cvv;

    @NotEmpty
    private String expireDate;

    @ManyToMany(cascade = {CascadeType.MERGE })
    List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco) {
        this.tacos.add(taco);
    }

    @ManyToOne
    private User user;

    private boolean status = false;
}
