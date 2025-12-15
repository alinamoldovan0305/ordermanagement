
package com.example.ordermanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.example.ordermanagement.model.ContractLine;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("PRODUCT")
public class Product extends SellableItem {
    @NotBlank(message = "Numele produsului este obligatoriu.")
    @Size(min = 2, max = 100, message = "Numele trebuie sa aiba intre 2 si 100 de caractere.")
    @Pattern(
            regexp = "^(?!\\d+$)[A-Za-z ]+$",
            message = "Numele trebuie sa contina doar litere si sa nu fie un numar."
    )
    private String name;

    @Min(value = 0, message = "Valoarea trebuie sa fie nenula")
    @Positive(message = "Valoarea trebuie sa fie mai mare decat 0.")
    private double value;

    @NotBlank(message = "Categoria este obligatorie.")
    @Size(max = 50, message = "Categoria nu poate avea mai mult de 50 de caractere")
    private String category;

    @Min(value = 0, message = "Stock-ul trebuie sa fie nenul")
    private int stock;

    public Product() {}

    public Product(String name, double value, String category, int stock) {
        super(name);
        this.value = value;
        this.category = category;
        this.stock = stock;
    }

    // GETTERS & SETTERS
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isInStock() {
        return stock > 0;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", value=" + value +
                ", category='" + category + '\'' +
                ", stock=" + stock +
                '}';
    }
}

