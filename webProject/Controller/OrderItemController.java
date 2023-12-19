package web.webProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.webProject.Service.ClientrService;
import web.webProject.Service.MenuService;
import web.webProject.Service.OrderItemService;
import web.webProject.Service.OrdersService;
import web.webProject.models.Client;
import web.webProject.models.Menu;
import web.webProject.models.OrderItem;
import web.webProject.models.Orders;

import java.util.List;

@Controller
public class OrderItemController {
    @Autowired
    OrderItemService orderItemService;
    @Autowired
    OrdersService ordersService;
    @Autowired
    MenuService menuService;
    @Autowired
    ClientrService clientrService;
    @GetMapping("/showNewOrderItem")
    public String showOrderItem(Model model){
        OrderItem orderItem=new OrderItem();
        List<Orders>ordersList=ordersService.listAllOrders();
        List<Menu>menuList=menuService.listAllMenus();
        model.addAttribute("orderItem",orderItem);
        model.addAttribute("ordersList",ordersList);
        model.addAttribute("menuList",menuList);
        return "orderItem";
    }
    @PostMapping("/saveOrderItem")
    public String saveOrderItem(@ModelAttribute("orderItem") OrderItem orderItem){
        orderItemService.saveOrderItem(orderItem);
        return "redirect:/showNewOrderItem";
    }
    @GetMapping("/orderItemDashBoard")
    public String orderItemDashBoard(Model model){
        List<OrderItem>ListOrderItem=orderItemService.listAllOrderItems();
        model.addAttribute("ListOrderItem",ListOrderItem);
        return "orderItemDashBoard";
    }
//    @GetMapping("/clientOrder")
//    public String clientOrder(Model model){
//        List<Client> clientList=clientrService.listAllUsers();
//        model.addAttribute("clientList", clientList);
//        return "ordering";
//    }
    @GetMapping("/updateOrderItem/{orderitemId}")
    public String updateOrderItem(@PathVariable(value = "orderitemId") Long orderitemId, Model model){
        OrderItem orderItem=orderItemService.findOrderItemById(orderitemId);
        List<Orders>orderList=ordersService.listAllOrders();
        List<Menu>menList=menuService.listAllMenus();
        model.addAttribute("orderList", orderList);
        model.addAttribute("menList", menList);
        model.addAttribute(orderItem);
        return "updateOrderItem";
    }
    @GetMapping("/deleteOrderItem/{orderitemId}")
    public String deleteOrderItem(@PathVariable(value = "orderitemId") Long orderitemId){
        this.orderItemService.deleteOrderitem(orderitemId);
        return "redirect:/orderItemDashBoard";
    }

}
