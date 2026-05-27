package pe.edu.upeu.api_orders.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {


    @Autowired
    private OrderService service;

    @PostMapping
    public ResponseEntity<Order> save(@RequestBody Order order) {
        return new ResponseEntity<>(service.createOrder(order), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Order> list() {
        return service.getAllOrders();
    }
}
