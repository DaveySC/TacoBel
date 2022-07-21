package com.example.servermonitoringservice;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class ServerMonitoringServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerMonitoringServiceApplication.class, args);
    }

}
