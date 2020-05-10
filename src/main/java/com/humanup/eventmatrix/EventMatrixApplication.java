package com.humanup.eventmatrix;

import com.humanup.eventmatrix.dao.EventDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAspectJAutoProxy
public class EventMatrixApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventMatrixApplication.class, args);
    }

}
