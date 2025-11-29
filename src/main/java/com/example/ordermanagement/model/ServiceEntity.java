//package com.example.ordermanagement.model;
//
//import com.example.ordermanagement.enums.ServiceStatus;
//
//public class ServiceEntity extends SellableItem {
//    private ServiceStatus status;
//
//    public ServiceEntity(String id, String name, ServiceStatus status ) {
//        super(id, name);
//        this.status = status;
//    }
//
//    public ServiceEntity() {}
//
//    public ServiceStatus getStatus() {
//        return status;
//    }
//
//    public void setStatus(ServiceStatus status) {
//        this.status = status;
//    }
//
//    @Override
//    public String toString() {
//        return "Service{" +
//                "id='" + getId() + '\'' +
//                ", name='" + getName() + '\'' +
//                ", status='" + status + '\'' +
//                '}';
//    }
//
//}

package com.example.ordermanagement.model;

import com.example.ordermanagement.enums.ServiceStatus;
import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SERVICE")
public class ServiceEntity extends SellableItem {

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
