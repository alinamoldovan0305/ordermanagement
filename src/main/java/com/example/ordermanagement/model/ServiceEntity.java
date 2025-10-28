package com.example.ordermanagement.model;

import com.example.ordermanagement.model.enums.ServiceStatus;

public class ServiceEntity extends SellableItem {
    private ServiceStatus status;

    public ServiceEntity(String id, String name, ServiceStatus status ) {
        super(id, name);
        this.status = status;
    }

    public ServiceEntity() {}

    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
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

