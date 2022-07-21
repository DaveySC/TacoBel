package com.example.tacobel.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderInfo {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private String address;
    @JsonProperty
    private String tacos;
    @JsonProperty
    private Calendar date;
    @JsonProperty
    private Courier courier;
}
