
package com.example.ordermanagement.service;

import com.example.ordermanagement.model.OrderLine;
import com.example.ordermanagement.model.Order;
import com.example.ordermanagement.model.UnitsOfMeasure;
import com.example.ordermanagement.repository.OrderLineRepository;
import com.example.ordermanagement.repository.OrderRepository;
import com.example.ordermanagement.repository.SellableItemRepository;
import com.example.ordermanagement.repository.UnitsOfMeasureRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final UnitsOfMeasureRepository unitRepository;
    private final SellableItemRepository itemRepository; // trebuie adăugat

    public OrderLineService(OrderLineRepository orderLineRepository,
                            OrderRepository orderRepository,
                            UnitsOfMeasureRepository unitRepository,
                            SellableItemRepository itemRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.unitRepository = unitRepository;
        this.itemRepository = itemRepository;
    }

    public List<OrderLine> getAll() {
        return orderLineRepository.findAll();
    }

    public OrderLine getById(Long id) {
        return orderLineRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("OrderLine not found with id: " + id));
    }


    public OrderLine save(OrderLine orderLine) {
        validateOrderLine(orderLine, true);
        return orderLineRepository.save(orderLine);
    }


    public OrderLine update(Long id, OrderLine updatedLine) {

        OrderLine existing = orderLineRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("OrderLine not found with id: " + id));

        updatedLine.setId(id);
        validateOrderLine(updatedLine, false);

        existing.setOrder(updatedLine.getOrder());
        existing.setItem(updatedLine.getItem());
        existing.setUnit(updatedLine.getUnit());
        existing.setQuantity(updatedLine.getQuantity());

        return orderLineRepository.save(existing);
    }

    public void delete(Long id) {

        OrderLine line = orderLineRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("OrderLine not found with id: " + id));

        if (line.getOrder().isDelivered()) {
            throw new IllegalStateException("Cannot delete an order line from a delivered order.");
        }

        orderLineRepository.deleteById(id);
    }




    private void validateOrderLine(OrderLine line, boolean isCreate) {

        if (line.getOrder() == null || line.getOrder().getId() == null ||
                !orderRepository.existsById(line.getOrder().getId())) {
            throw new IllegalArgumentException("Order does not exist!");
        }

        if (line.getItem() == null || line.getItem().getId() == null ||
                !itemRepository.existsById(line.getItem().getId())) {
            throw new IllegalArgumentException("Item does not exist!");
        }


        if (line.getUnit() == null || line.getUnit().getId() == null ||
                !unitRepository.existsById(line.getUnit().getId())) {
            throw new IllegalArgumentException("Unit of measure does not exist!");
        }

        if (line.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero.");
        }

        if (!isCreate && line.getOrder().isDelivered()) {
            throw new IllegalStateException("Cannot update order lines of a delivered order.");
        }
    }
}
