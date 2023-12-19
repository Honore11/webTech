package web.webProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.webProject.Service.OrdersService;
import web.webProject.Service.PaymentService;
import web.webProject.models.Orders;
import web.webProject.models.Payment;


import java.util.List;

@Controller
public class PaymentController {
    @Autowired
    PaymentService paymentService;
    @Autowired
    OrdersService ordersService;
    @GetMapping("/showNewPaymentForm")
    public String showPayment(Model model){
        Payment payment=new Payment();
        List<Orders>ordersList=ordersService.listAllOrders();
        model.addAttribute("payment",payment);
        model.addAttribute("ordersList",ordersList);
        return "payment";
    }
    @PostMapping("/savePayment")
    public String savePayment(@ModelAttribute("payment") Payment payment){
        paymentService.savePayment(payment);
        return "redirect:/showNewPaymentForm";
    }
    @GetMapping("/paymentDashBoard")
    public String paymentDashBoard(Model model){
        List<Payment>ListPayments=paymentService.listAllPayments();
        model.addAttribute("ListPayments",ListPayments);
        return "paymentDashBoard";
    }
    @GetMapping("/updatePayment/{paymentId}")
    public String updateOrder(@PathVariable(value = "paymentId") Long paymentId, Model model){
        Payment payment=paymentService.findPaymentById(paymentId);
        List<Orders>orderList=ordersService.listAllOrders();
        model.addAttribute("orderList", orderList);
        model.addAttribute(payment);
        return "updatePayment";
    }
    @GetMapping("/deletePayment/{paymentId}")
    public String deletePayment(@PathVariable(value = "paymentId") Long paymentId){
        this.paymentService.deletePayment(paymentId);
        return "redirect:/paymentDashBoard";
    }
}
