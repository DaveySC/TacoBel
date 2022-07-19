package com.example.deliveryservice.config;

import com.example.deliveryservice.entity.Courier;
import com.example.deliveryservice.repository.CourierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


@Component
public class ApplicationRunnerImpl implements ApplicationRunner {

    private CourierRepository courierRepository;

    @Autowired
    public void setCourierRepository(CourierRepository courierRepository) {
        this.courierRepository = courierRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        courierRepository.save(new Courier(null, "Danil", 0L, new ArrayList<>()));
    }
}
