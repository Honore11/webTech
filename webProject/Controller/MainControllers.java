package web.webProject.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControllers {

     @GetMapping("/showHome")
    public String showHome(){
         return "Home";
     }
     @GetMapping("/aboutUs")
     public String aboutUs(){
         return "AboutUs";
     }
     @GetMapping("/menu")
    public String menu(){
         return "explore";
     }
     @GetMapping("/contact")
    public String contact(){
         return "contact";
     }
     @GetMapping("/privacy")
    public String privacy(){
         return "privacy";
     }
     @GetMapping("/warning")
    public String warning(){
         return "warning";
     }
}
