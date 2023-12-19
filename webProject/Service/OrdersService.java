package web.webProject.Service;

import web.webProject.models.Orders;

import java.util.List;

public interface OrdersService {
    void saveOrders(Orders orders);

    List<Orders> getCompleteOrdersByClientId(Long clientId);
    List<Orders> getRejecteOrderSByClientId(Long clientId);
    List<Orders> getPendingOrderSbyClientId(Long clientID);
    List<Orders>listAllOrders();
    Orders findOrderById(Long orderId);
    void deleteOrder(Long orderId);
}
