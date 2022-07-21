package com.example.tacobel.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Courier {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String name;
    @JsonProperty
    private Long countOfMails;
}
