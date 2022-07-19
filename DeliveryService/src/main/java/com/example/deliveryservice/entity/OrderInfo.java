package com.example.deliveryservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.annotation.processing.Generated;
import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String address;

    private String tacos;

    @JsonIgnore
    private Calendar date = Calendar.getInstance();

    @ManyToOne
    @JsonIgnore
    private Courier courier;

    public void addTime(int minutes) {
        date.add(Calendar.MINUTE, minutes);
    }

}
