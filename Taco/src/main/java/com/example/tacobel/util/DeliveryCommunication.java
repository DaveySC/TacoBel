package com.example.tacobel.util;

import com.example.tacobel.entity.Ingredient;
import com.example.tacobel.entity.TacoOrder;
import com.example.tacobel.entity.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.HashMap;

public class DeliveryCommunication {

    private static final String URL = "http://localhost:8081/api/order/";

    private static <T> ResponseEntity<String> send(T data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<Object>(data,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        if (responseEntity.getStatusCode().equals(HttpStatus.OK)) return responseEntity;
        return null;
    }

    private static HashMap<String, String> convertOrderInfoToMessage(TacoOrder tacoOrder) {
        HashMap<String, String> message = new HashMap<>();
        User user = tacoOrder.getUser();
        message.put("name", user.getName());
        message.put("address", tacoOrder.getStreet());
        message.put("tacos", tacoOrder.getTacos().toString());
        message.put("id", tacoOrder.getId().toString());
        return message;
    }

    public static void send(TacoOrder tacoOrder) {
        send(convertOrderInfoToMessage(tacoOrder));
    }

}
