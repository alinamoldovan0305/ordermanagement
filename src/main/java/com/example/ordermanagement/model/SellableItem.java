
package com.example.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity  // nu @MappedSuperclass aici
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public abstract class SellableItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Numele produsului este obligatoriu.")
    @Size(min = 2, max = 100, message = "Numele trebuie sa aiba intre 2 si 100 de caractere.")
    @Pattern(
            regexp = "^(?!\\d+$)[A-Za-z ]+$",
            message = "Numele trebuie sa contina doar litere si sa nu fie un numar."
    )
    private String name;

    public SellableItem() {}

    public SellableItem(String name) {
        this.name = name;
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
}
