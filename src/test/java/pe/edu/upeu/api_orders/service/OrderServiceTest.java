package pe.edu.upeu.api_orders.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pe.edu.upeu.api_orders.model.Order;
import pe.edu.upeu.api_orders.repository.OrderRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private OrderRepository repository;

    @InjectMocks
    private OrderService service;

    @Test
    void whenAmountIsGreaterThan1000_thenApplyDiscount() {
        Order order = new Order(null, "Elvis Garcia", 1200.0);

        when(repository.save(any(Order.class))).thenAnswer(invocation -> {
            Order saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        Order saved = service.createOrder(order);

        assertNotNull(saved);
        assertEquals(1L, saved.getId());
        assertEquals("Elvis Garcia", saved.getCustomer());
        assertEquals(1080.0, saved.getAmount(), 0.001);

        verify(repository, times(1)).save(order);
    }

    @Test
    void whenAmountIsLessOrEqualThan1000_thenDoNotApplyDiscount() {
        Order order = new Order(null, "Cliente Normal", 800.0);

        when(repository.save(any(Order.class))).thenAnswer(invocation -> {
            Order saved = invocation.getArgument(0);
            saved.setId(2L);
            return saved;
        });

        Order saved = service.createOrder(order);

        assertNotNull(saved);
        assertEquals(2L, saved.getId());
        assertEquals("Cliente Normal", saved.getCustomer());
        assertEquals(800.0, saved.getAmount(), 0.001);

        verify(repository, times(1)).save(order);
    }

    @Test
    void whenGetAllOrders_thenReturnOrderList() {
        List<Order> orders = List.of(
                new Order(1L, "Cliente 1", 500.0),
                new Order(2L, "Cliente 2", 1080.0)
        );

        when(repository.findAll()).thenReturn(orders);

        List<Order> result = service.getAllOrders();

        assertEquals(2, result.size());
        assertEquals("Cliente 1", result.get(0).getCustomer());
        assertEquals("Cliente 2", result.get(1).getCustomer());

        verify(repository, times(1)).findAll();
    }
}