package web.webProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.webProject.Service.MenuService;
import web.webProject.Service.RestaurantService;
import web.webProject.models.Delivery;
import web.webProject.models.Menu;
import web.webProject.models.Orders;
import web.webProject.models.Restaurant;

import java.util.List;

@Controller
public class MenuController{
    @Autowired
    RestaurantService restaurantService;
 @Autowired
    MenuService menuService;
    @GetMapping("/showNewMenuForm")
    public String showMenuForm(Model model){
        Menu menu= new Menu();
        List<Restaurant>restaurantList=restaurantService.listAllRestaurant();
        model.addAttribute("menu", menu);
        model.addAttribute("restaurantList",restaurantList);
        return "menu";
    }
    @PostMapping("/saveMenu")
    public String saveMenu(@ModelAttribute("menu") Menu menu){
        menuService.saveMenu(menu);
        return "redirect:/showNewMenuForm";
    }

    @GetMapping("/menuDashBoard")
    public String menuDashBoar(Model model, String keyword){
        List<Menu>ListMenu=menuService.listAllMenus();
        if (keyword != null){
            model.addAttribute("ListMenu", menuService.findByKeyword(keyword));
        } else {
            model.addAttribute("ListMenu",ListMenu);
        }
        return "menuDashBoard";
    }

    @GetMapping("/menDashBoard")
    public String menDashBoa(Model model){
        List<Menu>ListMenu=menuService.listAllMenus();
        model.addAttribute("ListMen",ListMenu);
        return "MenuItems";
    }
    @GetMapping("/meDashBoard")
    public String menDshBoa(Model model){
        List<Menu>LisMenu=menuService.listAllMenus();
        model.addAttribute("LisMen",LisMenu);
        return "explore";
    }

    @GetMapping("/updateMenu/{itemId}")
    public String updateMenu(@PathVariable(value = "itemId") Long itemId, Model model){
        Menu menu=menuService.findMenuById(itemId);
        List<Restaurant>restoList=restaurantService.listAllRestaurant();
        model.addAttribute("restoList", restoList);
        model.addAttribute(menu);
        return "updateMenu";
    }
    @GetMapping("/deleteMenu/{itemId}")
    public String deleteMenu(@PathVariable(value = "itemId") Long itemId){
        this.menuService.deleteMenu(itemId);
        return "redirect:/menuDashBoard";
    }
}
