package web.webProject.Service;

import web.webProject.models.OrderItem;

import java.util.List;

public interface OrderItemService {
    void saveOrderItem(OrderItem orderItem);
    List<OrderItem>listAllOrderItems();
    OrderItem findOrderItemById(Long itemId);
    void deleteOrderitem(Long itemId);
}
