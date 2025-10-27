package com.example.ordermanagement.repository;

import com.example.ordermanagement.model.SellableItem;
import com.example.ordermanagement.model.Service;
import java.util.*;

public class ServiceRepository {

    private Map<String, Service> services = new HashMap<>();

    public void save(Service service) {
        services.put(service.getId(), service);
    }

    public List<Service> findAll() {
        return new ArrayList<>(services.values());
    }

    public Service findById(String id) {
        return services.get(id);
    }

    public void delete(String id) {
        services.remove(id);
    }

    public void update(String id, Service updatedService) {
        if(services.containsKey(id)) {
            services.put(id, updatedService);
        }
    }
}
