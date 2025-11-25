//package com.example.ordermanagement.model;
//
//public class ContractType {
//    private String id;
//    private String name;
//    private String type;
//
//    public ContractType(String id, String name, String type) {
//        this.id = id;
//        this.name = name;
//        this.type = type;
//    }
//
//    public ContractType () {}
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
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    @Override
//    public String toString() {
//        return "ContractType{" +
//                "id='" + id + '\'' +
//                ", name='" + name + '\'' +
//                ", type='" + type + '\'' +
//                '}';
//    }
//
//}
package com.example.ordermanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "contract_types")
public class ContractType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // înlocuit String -> Long pentru baza de date

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String type;

    public ContractType() {}

    public ContractType(String name, String type) {
        this.name = name;
        this.type = type;
    }

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContractType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
