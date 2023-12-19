package web.webProject.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webProject.Repository.OrderItemRepo;
import web.webProject.Service.OrderItemService;
import web.webProject.models.OrderItem;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImple implements OrderItemService {
    OrderItemRepo orderItemRepo;

    @Autowired
    public OrderItemServiceImple(OrderItemRepo orderItemRepo){
        this.orderItemRepo=orderItemRepo;
    }
    @Override
    public void saveOrderItem(OrderItem orderItem) {
        this.orderItemRepo.save(orderItem);
    }

    @Override
    public List<OrderItem> listAllOrderItems() {
        return this.orderItemRepo.findAll();
    }

    @Override
    public OrderItem findOrderItemById(Long itemId) {
        Optional<OrderItem> optional=orderItemRepo.findById(itemId);
        OrderItem orderItem=null;
        if (optional.isPresent()){
            orderItem=optional.get();
        }else {
            throw new RuntimeException("Order Item Is not Found");
        }
        return orderItem;
    }

    @Override
    public void deleteOrderitem(Long itemId) {
        this.orderItemRepo.deleteById(itemId);
    }
}
