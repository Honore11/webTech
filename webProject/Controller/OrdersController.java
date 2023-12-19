package web.webProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.webProject.Service.EmailService;
import web.webProject.Service.OrdersService;
import web.webProject.Service.ClientrService;
import web.webProject.models.Client;
import web.webProject.models.Orders;


import java.util.List;

@Controller
public class OrdersController {

    @Autowired
    @Qualifier("emailServiceImplementation")
    private EmailService emailService;
    @Autowired
    ClientrService clientrService;
    @Autowired
    OrdersService ordersService;
    @GetMapping("/showNewOrders")
    public String showOrders(Model model){
        Orders orders= new Orders();
        List<Client>userList= clientrService.listAllUsers();
        model.addAttribute("orders", orders);
        model.addAttribute("userList", userList);

        return "order";
    }
    @PostMapping("/saveOrders")
    public String saveOrder(@ModelAttribute("orders") Orders orders){
        ordersService.saveOrders(orders);
         return "redirect:/orderDashBoard";
    }
    @GetMapping("/orderDashBoard")
    public String orderDashBoard(Model model){
        List<Orders>ListOrders=ordersService.listAllOrders();
        model.addAttribute("ListOrders", ListOrders);
        return "orderDashBoard";
    }
    @GetMapping("/showNeworderForm")
    public String showOrde(Model model){
        Orders orders = new Orders();
        List<Client> clientList = clientrService.listAllUsers();
        model.addAttribute("orders", orders);
        Client client = new Client();
        model.addAttribute("client", client);
        return "orderi";
    }

    @PostMapping("/saveOrder")
    public String saverder(@ModelAttribute("ordrs") Orders orders){
        ordersService.saveOrders(orders);
        return "redirect:/showNeworderForm";
    }

    @GetMapping("/updateOrder/{order_Id}")
    @ResponseBody
    public String updateOrder(@PathVariable(value = "order_Id") Long order_Id, Model model){
        Orders orderss = new Orders();
        List<Client>LiClientList = clientrService.listAllUsers();
        Orders orders = ordersService.findOrderById(order_Id);
        Client client = orders.getClient();
        String ClientEimal = client.getMail();
        model.addAttribute("LiClientList", LiClientList);
        model.addAttribute("orderss",orderss);
        String subject = "Order Is Approved";
        String text = "I trust this message finds you well. We are thrilled to inform you that your recent order with Fast food has been successfully processed and is now in the final stages of preparation for shipment.";
        emailService.sendEmail(ClientEimal,subject,text);
        return "updateOrder";
    }
    @GetMapping("/deleteOrder/{order_Id}")
    public String deleteOrder(@PathVariable(value = "order_Id") Long order_Id){
        this.ordersService.deleteOrder(order_Id);
        return "redirect:/orderDashBoard";
    }

}
