package web.webProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.webProject.Service.DeliveryService;
import web.webProject.Service.OrdersService;
import web.webProject.models.Delivery;
import web.webProject.models.Orders;

import java.util.List;

@Controller
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    OrdersService ordersService;
    @GetMapping("/showNewDeliveryForm")
    public String showDelivery(Model model){
        Delivery delivery=new Delivery();
        List<Orders>ordersList=ordersService.listAllOrders();
        model.addAttribute("delivery", delivery);
        model.addAttribute("ordersList", ordersList);
        return "delivery";
    }
    @PostMapping("/saveDelivery")
    public String saveDelivery(@ModelAttribute("delivery") Delivery delivery){
        deliveryService.saveDelivery(delivery);
        return "redirect:/showNewDeliveryForm";
    }
    @GetMapping("/deliveryDashBoard")
    public String deliveryDashboard(Model model){
        List<Delivery>ListDeliveries=deliveryService.lsiDeliveries();
        model.addAttribute("ListDeliveries", ListDeliveries);
        return "deliveryDashBoard";
    }
    @GetMapping("/updateDelivery/{deliveryId}")
    public String updateDelivery(@PathVariable(value = "deliveryId") Long deliveryId, Model model){
        Delivery delivery=deliveryService.findDeliveryById(deliveryId);
        List<Orders>orderList=ordersService.listAllOrders();
        model.addAttribute("orderList", orderList);
        model.addAttribute(delivery);
        return "updateDelivery";
    }
    @GetMapping("/deleteDelivery/{deliveryId}")
    public String deleteDelivery(@PathVariable(value = "deliveryId") Long deliveryId){
        this.deliveryService.deleteDelivery(deliveryId);
        return "redirect:/deliveryDashBoard";
    }
}
