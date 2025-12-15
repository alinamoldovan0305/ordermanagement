
package com.example.ordermanagement.model;

import com.example.ordermanagement.enums.ServiceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("SERVICE")
public class ServiceEntity extends SellableItem {

    @NotNull(message = "Service status is required")
    @Enumerated(EnumType.STRING)
    private ServiceStatus status;


    public ServiceEntity() {}

    public ServiceEntity(String name, ServiceStatus status) {
        super(name);
        this.status = status;
    }

    // GETTERS & SETTERS
    public ServiceStatus getStatus() {
        return status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServiceEntity{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", status=" + status +
                '}';
    }
}
