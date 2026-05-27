package pe.edu.upeu.api_orders.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.repository.OrderRepository;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public Order createOrder(Order order) {
        // Lógica: Si el monto es mayor a 1000, aplicar 10% de descuento
        if (order.getAmount() > 1000) {
            order.setAmount(order.getAmount() * 0.9);
        }
        return repository.save(order);
    }

    public List<Order> getAllOrders() {
        return repository.findAll();
    }
}
