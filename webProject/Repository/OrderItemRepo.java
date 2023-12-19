package web.webProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.webProject.models.OrderItem;

@Repository
public interface OrderItemRepo extends JpaRepository<OrderItem,Long> {
}
