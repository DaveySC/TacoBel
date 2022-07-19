package com.example.deliveryservice.rest;


import com.example.deliveryservice.entity.OrderInfo;
import com.example.deliveryservice.repository.CourierRepository;
import com.example.deliveryservice.repository.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderMessageRestController {

    private OrderInfoRepository orderInfoRepository;

    private CourierRepository courierRepository;

    @Autowired
    public void setOrderInfoRepository(OrderInfoRepository orderInfoRepository,
                                       CourierRepository courierRepository) {
        this.orderInfoRepository = orderInfoRepository;
        this.courierRepository = courierRepository;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void deliveryOrder(HttpEntity<OrderInfo> entity) {
        OrderInfo orderInfo = entity.getBody();
        assert orderInfo != null;
        orderInfo.addTime(30);
        orderInfo.setCourier(courierRepository.findCourierWithMinMails());
        orderInfoRepository.save(orderInfo);
    }

    @PostMapping("/{id}")
    public String getOrderStatus(@PathVariable String id) {
        Optional<OrderInfo> info = orderInfoRepository.findById(Long.valueOf(id));
        if (info.isPresent()) {
            return info.get().getDate().toString();
        }
        return HttpStatus.NOT_FOUND.toString();
    }

}
