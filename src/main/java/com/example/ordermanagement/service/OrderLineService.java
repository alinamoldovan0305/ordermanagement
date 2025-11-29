//package com.example.ordermanagement.service;
//
//import com.example.ordermanagement.model.OrderLine;
//import com.example.ordermanagement.repository.OrderLineRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OrderLineService extends GenericService<OrderLine> {
//    public OrderLineService(OrderLineRepository repository) {
//        super(repository);
//    }
//}
//
//
package com.example.ordermanagement.service;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.repository.OrderLineRepository;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final UnitsOfMeasureRepository unitRepository;

    public OrderLineService(OrderLineRepository orderLineRepository,
                            OrderRepository orderRepository,
                            UnitsOfMeasureRepository unitRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.unitRepository = unitRepository;
    }

    public List<OrderLine> getAll() {
        return orderLineRepository.findAll();
    }

    public OrderLine getById(Long id) {
        return orderLineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderLine not found with id: " + id));
    }

    public OrderLine save(OrderLine orderLine) {
        validateOrderLineRelations(orderLine);
        return orderLineRepository.save(orderLine);
    }

    public OrderLine update(Long id, OrderLine updatedLine) {
        OrderLine existing = orderLineRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderLine not found with id: " + id));

        validateOrderLineRelations(updatedLine);

        existing.setOrder(updatedLine.getOrder());
        existing.setItem(updatedLine.getItem());
        existing.setUnit(updatedLine.getUnit());
        existing.setQuantity(updatedLine.getQuantity());

        return orderLineRepository.save(existing);
    }

    public void delete(Long id) {
        if (!orderLineRepository.existsById(id)) {
            throw new EntityNotFoundException("OrderLine not found with id: " + id);
        }
        orderLineRepository.deleteById(id);
    }

    private void validateOrderLineRelations(OrderLine line) {
        if (line.getOrder() == null || line.getOrder().getId() == null ||
                !orderRepository.existsById(line.getOrder().getId())) {
            throw new IllegalArgumentException("Order does not exist!");
        }

        if (line.getUnit() == null || line.getUnit().getId() == null ||
                !unitRepository.existsById(line.getUnit().getId())) {
            throw new IllegalArgumentException("Unit of measure does not exist!");
        }
    }
}
