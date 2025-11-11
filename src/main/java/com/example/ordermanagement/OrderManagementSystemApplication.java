package com.example.ordermanagement;

import com.example.ordermanagement.model.*;
import com.example.ordermanagement.model.enums.ContractStatus;
import com.example.ordermanagement.model.enums.ServiceStatus;
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

    @Bean
    public CommandLineRunner initData(
            UnitsOfMeasureService unitsService,
            ProductService productService,
            ServiceService serviceService,
            ContractTypeService contractTypeService,
            ContractService contractService,
            CustomerService customerService,
            ContractLineService contractLineService,
            OrderService orderService,
            OrderLineService orderLineService // adăugat acum
    ) {
        return args -> {


            // ---------- PRODUSE ----------
            Product p1 = new Product("P1", "Laptop", 1200.0, "Electronice", 10);
            Product p2 = new Product("P2", "Telefon", 800.0, "Electronice", 25);
            Product p3 = new Product("P3", "Căști", 150.0, "Accesorii", 6);

            productService.add(p1.getId(), p1);
            productService.add(p2.getId(), p2);
            productService.add(p3.getId(), p3);

            // ---------- SERVICII ----------
            ServiceEntity s1 = new ServiceEntity("S1", "Instalare", ServiceStatus.ACTIVE);
            ServiceEntity s2 = new ServiceEntity("S2", "Reparație", ServiceStatus.PENDING);
            ServiceEntity s3 = new ServiceEntity("S3", "Întreținere", ServiceStatus.SUSPENDED);

            serviceService.add(s1.getId(), s1);
            serviceService.add(s2.getId(), s2);
            serviceService.add(s3.getId(), s3);

            // ---------- ELEMENTE VANDABILE (SELLABLE ITEMS) ----------
            // adică produse și servicii în aceeași categorie abstractă
            SellableItem si1 = p1;
            SellableItem si2 = s1;
            SellableItem si3 = p2;

            // ---------- TIPURI DE CONTRACT ----------
            ContractType ct1 = new ContractType("CT1", "Standard", "Service");
            ContractType ct2 = new ContractType("CT2", "Premium", "Product");

            contractTypeService.add(ct1.getId(), ct1);
            contractTypeService.add(ct2.getId(), ct2);

            // ---------- CONTRACTE ----------
            Contract c1 = new Contract("C1", "Contract A", "ct1", ContractStatus.ACTIVE);
            Contract c2 = new Contract("C2", "Contract B", "ct2", ContractStatus.CANCELLED);
            Contract c3 = new Contract("C3", "Contract C", "ct3", ContractStatus.EXPIRED);

            contractService.add(c1.getId(), c1);
            contractService.add(c2.getId(), c2);
            contractService.add(c3.getId(), c3);

            // ---------- CLIENȚI ----------
            Customer cust1 = new Customer("CU1", "Alina", "EUR", "alina@mail.com", "0722123456");
            Customer cust2 = new Customer("CU2", "Carla", "USD", "carla@mail.com", "0722456789");
            Customer cust3 = new Customer("CU3", "Andrei", "RON", "andrei@mail.com", "0722987654");

            customerService.add(cust1.getId(), cust1);
            customerService.add(cust2.getId(), cust2);
            customerService.add(cust3.getId(), cust3);

            // ---------- UNITĂȚI DE MĂSURĂ ----------
            UnitsOfMeasure u1 = new UnitsOfMeasure("U1", "Bucată", "buc");
            UnitsOfMeasure u2 = new UnitsOfMeasure("U2", "Kilogram", "kg");
            UnitsOfMeasure u3 = new UnitsOfMeasure("U3", "Metru pătrat", "m²");

            unitsService.add(u1.getId(), u1);
            unitsService.add(u2.getId(), u2);
            unitsService.add(u3.getId(), u3);

            // ---------- LINII DE CONTRACT ----------
            ContractLine cl1 = new ContractLine("CL1", p1, u1, 2);
            ContractLine cl2 = new ContractLine("CL2", s1, u2, 5);
            ContractLine cl3 = new ContractLine("CL3", p2, u3, 1);

            contractLineService.add(cl1.getId(), cl1);
            contractLineService.add(cl2.getId(), cl2);
            contractLineService.add(cl3.getId(), cl3);

            // ---------- COMENZI ----------
            Order o1 = new Order("O1", "Comandă 101", cust1, c1, LocalDate.now(), false);
            Order o2 = new Order("O2", "Comandă 202", cust2, c2, LocalDate.of(2025, 11, 4), true);
            Order o3 = new Order("O3", "Comandă 303", cust3, c3, LocalDate.of(2025, 11, 5), false);

            orderService.add(o1.getId(), o1);
            orderService.add(o2.getId(), o2);
            orderService.add(o3.getId(), o3);

            // ---------- LINII DE COMANDĂ ----------
            OrderLine ol1 = new OrderLine("OL1", si1, u1, 1);
            OrderLine ol2 = new OrderLine("OL2", si2, u2, 3);
            OrderLine ol3 = new OrderLine("OL3", si3, u3, 2);

            orderLineService.add(ol1.getId(), ol1);
            orderLineService.add(ol2.getId(), ol2);
            orderLineService.add(ol3.getId(), ol3);

            System.out.println(" Datele inițiale au fost adăugate cu succes!");
        };
    }
}

