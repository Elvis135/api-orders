package pe.edu.upeu.api_orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upeu.api_orders.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
