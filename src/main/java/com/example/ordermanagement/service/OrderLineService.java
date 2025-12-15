
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
                        new EntityNotFoundException("Nu s-a gasit linia de comanda cu id: " + id));
    }


    public OrderLine save(OrderLine orderLine) {
        validateOrderLine(orderLine, true);
        return orderLineRepository.save(orderLine);
    }


    public OrderLine update(Long id, OrderLine updatedLine) {

        OrderLine existing = orderLineRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException("Nu s-a gasit linia de comanda cu id: " + id));

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
                        new EntityNotFoundException("Nu s-a gasit linia de comanda cu id: " + id));

        if (line.getOrder().isDelivered()) {
            throw new IllegalStateException("Nu se poate sterge linia de comanda a unei comenzi livrate.");
        }

        orderLineRepository.deleteById(id);
    }




    private void validateOrderLine(OrderLine line, boolean isCreate) {

        if (line.getOrder() == null || line.getOrder().getId() == null ||
                !orderRepository.existsById(line.getOrder().getId())) {
            throw new IllegalArgumentException("Comanda nu exista!");
        }

        if (line.getItem() == null || line.getItem().getId() == null ||
                !itemRepository.existsById(line.getItem().getId())) {
            throw new IllegalArgumentException("Obiectul nu exista!");
        }


        if (line.getUnit() == null || line.getUnit().getId() == null ||
                !unitRepository.existsById(line.getUnit().getId())) {
            throw new IllegalArgumentException("Unitatea de masura nu exista!");
        }

        if (line.getQuantity() <= 0) {
            throw new IllegalArgumentException("Cantitatea trebuie sa fie mai mare de 0.");
        }

        if (!isCreate && line.getOrder().isDelivered()) {
            throw new IllegalStateException("Nu se poate modifica linia de comanda a unei comenzi livrate.");
        }
    }
}
