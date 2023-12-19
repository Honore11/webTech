package web.webProject.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webProject.Repository.OrdersRepo;
import web.webProject.Service.OrdersService;
import web.webProject.models.Client;
import web.webProject.models.Estatus;
import web.webProject.models.Orders;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersServiceImple implements OrdersService {

    OrdersRepo ordersRepo;
    @Autowired
    public OrdersServiceImple(OrdersRepo ordersRepo){
        this.ordersRepo=ordersRepo;
    }
    @Override
    public void saveOrders(Orders orders) {
        this.ordersRepo.save(orders);
    }

    @Override
    public List<Orders> getCompleteOrdersByClientId(Long clientId) {
        Client client = new Client();
        client.setClientId(clientId);
        Estatus estatus = Estatus.Complete;
        return ordersRepo.findByClientAndEstatus(client,estatus);
    }

    @Override
    public List<Orders> getRejecteOrderSByClientId(Long clientId) {
        Client client = new Client();
        client.setClientId(clientId);
        Estatus estatus = Estatus.Failed;
        return ordersRepo.findByClientAndEstatus(client,estatus);

    }

    @Override
    public List<Orders> getPendingOrderSbyClientId(Long clientID) {
        Client client = new Client();
        client.setClientId(clientID);
        Estatus estatus = Estatus.Pending;
        return ordersRepo.findByClientAndEstatus(client,estatus);
    }

    @Override
    public List<Orders> listAllOrders() {
        return this.ordersRepo.findAll();
    }

    @Override
    public Orders findOrderById(Long orderId) {
        Optional<Orders> optional=ordersRepo.findById(orderId);
        Orders orders=null;
        if (optional.isPresent()){
            orders=optional.get();
        }else {
            throw new RuntimeException("Order Is Not Found");
        }
        return orders;
    }

    @Override
    public void deleteOrder(Long orderId) {
        this.ordersRepo.deleteById(orderId);
    }
}
