package com.example.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele este obligatoriu")
    @Size(max = 100, message = "Numele nu poate avea mai mult de 100 de caractere")
    private String name;

    private String currency;

    @NotBlank(message = "Email-ul e obligatoriu")
    @Email(message = "Email trebuie sa fie valid")
    private String email;

    @NotBlank(message = "Numarul de telefon e obligatoriu")
    @Size(max = 20, message = "Numarul de telefon trebuie sa fie valid si sa nu aiba mai mult de 20 de caractere")
    private String phonenumber;


    // Relația OneToMany cu Orders
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders = new ArrayList<>();

    // Relația OneToMany cu Contracts
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Contract> contracts = new ArrayList<>();

    // Constructori
    public Customer() {
    }

    public Customer(String name, String currency, String email, String phonenumber) {
        this.name = name;
        this.currency = currency;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    public void removeOrder(Order order) {
        orders.remove(order);
        order.setCustomer(null);
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void addContract(Contract contract) {
        contracts.add(contract);
        contract.setCustomer(this);
    }

    public void removeContract(Contract contract) {
        contracts.remove(contract);
        contract.setCustomer(null);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", email='" + email + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", orders=" + orders.size() +
                ", contracts=" + contracts.size() +
                '}';
    }
}

