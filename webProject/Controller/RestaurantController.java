package web.webProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.webProject.Service.RestaurantService;
import web.webProject.models.Orders;
import web.webProject.models.Payment;
import web.webProject.models.Restaurant;

import java.util.List;

@Controller
public class RestaurantController {
    @Autowired
    RestaurantService restaurantService;
    @GetMapping("/showNewRestaurant")
    public String showRestaurant(Model model){
        Restaurant restaurant= new Restaurant();
      model.addAttribute("restaurant", restaurant);
      return "restaurant";
    }
    @PostMapping("/saveRestaurant")
    public String saveRestaurant(@ModelAttribute("restaurant") Restaurant restaurant){
        restaurantService.saveRestaurant(restaurant);
        return "redirect:/showNewRestaurant";
    }
    @GetMapping("/restaurantDashBoard")
    public String restaurantDashBoard(Model model){
        List<Restaurant>ListRestaurant=restaurantService.listAllRestaurant();
        model.addAttribute("ListRestaurant", ListRestaurant);
        return "restaurantDashBoard";
    }
////    @GetMapping("/paymentDashBoard")
////    public String paymentDashBoard(Model model){
////        List<Payment>ListPayments=paymentService.listAllPayments();
////        model.addAttribute("ListPayments",ListPayments);
////        return "paymentDashBoard";
//    }
    @GetMapping("/updateRestaurant/{restoId}")
    public String updateOrder(@PathVariable(value = "restoId") Long restoId, Model model){
        Restaurant restaurant=restaurantService.findRestobyId(restoId);
        model.addAttribute(restaurant);
        return "updateRestaurant";
    }
    @GetMapping("/deleteRestaurant/{restoId}")
    public String deletePayment(@PathVariable(value = "restoId") Long restoId){
        this.restaurantService.deleteResto(restoId);
        return "redirect:/restaurantDashBoard";
    }
}
