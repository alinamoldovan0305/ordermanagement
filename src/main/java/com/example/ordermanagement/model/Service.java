package com.example.ordermanagement.model;

public class Service extends SellableItem {
    private String status;

    public Service(String id, String name, String status ) {
        super(id, name);
        this.status = status;
    }

    public Service() {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Service{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

}

