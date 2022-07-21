package com.example.tacobel.util;

import com.example.tacobel.entity.Ingredient;
import com.example.tacobel.entity.TacoOrder;
import com.example.tacobel.entity.User;
import com.example.tacobel.pojo.OrderInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.criterion.Order;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class DeliveryCommunication {

    private static final String URL_SAVE_ORDER = "http://localhost:8081/api/order/";
    private static final String URL_GET_ORDER_STATUS = "http://localhost:8081/api/order/id";


    private static <T> ResponseEntity<String> send(T data, String url) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<?> entity = new HttpEntity<Object>(data,headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
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
        send(convertOrderInfoToMessage(tacoOrder), URL_SAVE_ORDER);
    }


    public static List<OrderInfo> send(List<Long> id) throws JsonProcessingException {
        ResponseEntity<String> response = send(id, URL_GET_ORDER_STATUS);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        OrderInfo[] test = (objectMapper.readValue(response.getBody(), OrderInfo[].class));
        return Arrays.asList(test);
    }


}
