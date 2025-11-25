//package com.example.ordermanagement.model;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Customer {
//    private String id;
//    private String name;
//    private String currency;
//    private List<Order> orders = new ArrayList<>();
//    private List<Contract> contracts =  new ArrayList<>();
//
//    private String email;
//    private String phonenumber;
//
//    public Customer(String id, String name, String currency,  String email, String phonenumber) {
//        this.id = id;
//        this.name = name;
//        this.currency = currency;
//        this.email= email;
//        this.phonenumber = phonenumber;
//    }
//
//    public Customer() {}
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCurrency() {
//        return currency;
//    }
//
//    public void setCurrency(String currency) {
//        this.currency = currency;
//    }
//
//    public List<Order> getOrders() {
//        return orders;
//    }
//
//    public void addOrders(Order order) {
//        this.orders.add(order);
//    }
//
//    public List<Contract> getContracts() {
//        return contracts;
//    }
//
//    public void addContracts(Contract contract) {
//        this.contracts.add(contract);
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//    public String getPhonenumber() {
//        return phonenumber;
//    }
//    public void setPhonenumber(String phonenumber) {
//        this.phonenumber = phonenumber;
//    }
//
//    @Override
//    public String toString() {
//        return "Customer{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", currency='" + currency + '\'' +
//                ", email='" + email + '\'' +
//                ", phoneNumber='" + phonenumber + '\'' +
//                ", orders=" + orders.size() +
//                ", contracts=" + contracts.size() +
//                '}';
//    }
//
//}
package com.example.ordermanagement.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // înlocuit String -> Long pentru compatibilitate DB

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 10)
    private String currency;

    @Column(nullable = false, unique = true, length = 120)
    private String email;

    @Column(name = "phone_number", nullable = false, length = 20)
    private String phonenumber;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Contract> contracts = new ArrayList<>();

    public Customer() {}

    public Customer(String name, String currency, String email, String phonenumber) {
        this.name = name;
        this.currency = currency;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    // ------------ Getters & Setters ------------

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
        order.setCustomer(this);  // sincronizare relație
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void addContract(Contract contract) {
        contracts.add(contract);
        contract.setCustomer(this);  // sincronizare relație
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phonenumber + '\'' +
                ", orders=" + orders.size() +
                ", contracts=" + contracts.size() +
                '}';
    }
}
