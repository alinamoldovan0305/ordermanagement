package com.example.ordermanagement;

import com.example.ordermanagement.model.*;
import com.example.ordermanagement.enums.ContractStatus;
import com.example.ordermanagement.enums.ServiceStatus;
import com.example.ordermanagement.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;

@SpringBootApplication
public class OrderManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderManagementSystemApplication.class, args);
    }

}

