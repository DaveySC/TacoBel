package com.example.deliveryservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String address;
    @JsonProperty
    @Column(length = 2048)
    private String tacos;

    @JsonProperty
    private Calendar date = Calendar.getInstance();

    @ManyToOne
    @JsonProperty
    private Courier courier;

    public void addTime(int minutes) {
        date.add(Calendar.MINUTE, minutes);
    }

}
