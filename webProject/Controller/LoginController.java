package web.webProject.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import web.webProject.Service.*;
import web.webProject.models.AdminLogin;
import web.webProject.models.Client;
import web.webProject.models.Menu;
import web.webProject.models.Orders;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private ClientrService clientrService;
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private MenuService menuService;
    @Autowired
    private AdminLoginService adminLoginService;

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("client", new Client());
        return "login";
    }

    @GetMapping("/loginOut")
    public String showLoginOutForm(Model model) {
        model.addAttribute("client", new Client());
        return "Login";
    }

    @GetMapping("/loginAdmin")
    public String showLoginAdminForm(Model model) {
        model.addAttribute("admin", new AdminLogin());
        return "AdminLogin";
    }

    @GetMapping("/MenuForm")
    public String showMenuForm(Model model) {
        Menu car = new Menu();
        model.addAttribute("car", car);
        return "menu";
    }

    @GetMapping("/showNewDashForm")
    public String showDashBashBoard(Model model) {
        return "DashBoard";
    }

    @PostMapping("/loginClient")
    public String login(@RequestParam String clientname, @RequestParam String password, Model model, HttpSession session) {
        if (clientrService.authenticateClient(clientname, password)) {
            Client client = clientrService.getClientClientname(clientname);
            Long clientId = client.getClientId();
            session.setAttribute("clientId", clientId);
            return "redirect:/MenuViewBooking";
        } else {
            model.addAttribute("error", "Username and password are incorrect");
            return "redirect:/login";
        }
    }

    @PostMapping("/loginAdmin")
    public String loginAdmin(@RequestParam String username, @RequestParam String passwor, Model model, HttpSession session) {
        if (adminLoginService.authenticateAdmin(username, passwor)) {
            AdminLogin adminLogin = adminLoginService.getAdmin(username);
            Long Id = adminLogin.getId();
            session.setAttribute("Id", Id);
            return "redirect:/showNewDashForm";
        } else {
//            model.addAttribute("error", "Username and password are incorrect");
            return "redirect:/loginAdmin";
        }
    }

    @GetMapping("/MenuViewBooking")
    public String MenuView(Model model, HttpSession session) {
        List<Menu> allMenu = menuService.listAllMenus();
        List<Client> clients = clientrService.listAllUsers();
        model.addAttribute("allMenu", allMenu);
        Long clientId = (Long) session.getAttribute("clientId");
        Long menuId = (Long) session.getAttribute("menuId");
        Client name = clientrService.findbyId(clientId);
        String clientName = name.getClientname();
        model.addAttribute("clients", clients);
        model.addAttribute("clientName", clientName);
        model.addAttribute("clientId", clientId);
        model.addAttribute("menuId", menuId);

        return "MenuItems";
    }

    @GetMapping("/barOnTheTop")
    public String showClientTopBar(Model model, HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
        model.addAttribute("clientId", clientId);
        return "barOnTheTop";
    }

    @GetMapping("/getordered")
    public String getordered(Model model, HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
        List<Orders> orderedClient = ordersService.listAllOrders();
        Client name = clientrService.findbyId(clientId);
        String clientName = name.getClientname();
        model.addAttribute("clientName", clientName);
        List<Orders> approved = orderedClient.stream()
                .filter(orders -> orders.getEstatus().equals("Complete") && orders.getClient().getClientId().equals(clientId))
                .collect(Collectors.toList());
        model.addAttribute("approved", approved);
        model.addAttribute("clientId", clientId);

        return "getordered";
    }

    @GetMapping("/clientInformation")
    public String clientInform(Model model, HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
        Client clientInfo = clientrService.findbyId(clientId);
        Client name = clientrService.findbyId(clientId);
        String clientName = name.getClientname();
        model.addAttribute("clientName", clientName);
        model.addAttribute("clientInfo", clientInfo);
        return "InformationClient";
    }

    @GetMapping("/clientInformationUpdate")
    public String clientInformationUpdate(Model model, HttpSession session) {
        Long clientId = (Long) session.getAttribute("clientId");
        Client clientInfo1 = clientrService.findbyId(clientId);
        Client name = clientrService.findbyId(clientId);
        String clientName = name.getClientname();
        model.addAttribute("clientName", clientName);
        model.addAttribute("clientInfo1", clientInfo1);

        return "InformationUpdate";
    }

    @PostMapping("/saveClientInfo")
    public String saveClientInfo(@ModelAttribute("client") Client client, HttpSession session, Model model) {
        clientrService.saveUser(client);
        Long clientId = (Long) session.getAttribute("clientId");
        Client client1 = clientrService.findbyId(clientId);
        Client name = clientrService.findbyId(clientId);
        String clientName = name.getClientname();
        model.addAttribute("clientName", clientName);
        model.addAttribute("client1", client1);

        return "redirect:/clientInformationUpdate";
    }

    @GetMapping("/showNewOrderingsForm")
    public String showOrderings(@RequestParam Long clientId, @RequestParam Long orderId, @RequestParam Long itemId, Model model) {
        Orders orders = new Orders();
        Client client = clientrService.findbyId(clientId);
        Menu menu = menuService.findMenuById(itemId);
        model.addAttribute("orders", orders);
        model.addAttribute("client", client);
        model.addAttribute("menu", menu);
        return "ordering";
    }

    @GetMapping("/showNewOrde")
    public String showOrders(@RequestParam Long clientId, Long itemId, Model model) {
        Orders orders = new Orders();
        Client client = clientrService.findbyId(clientId);
        model.addAttribute("orders", orders);
        model.addAttribute("clientId", clientId);
        return "orderr";
    }

    @PostMapping("/saveOrde")
    public String saveOrder(@ModelAttribute("orders") Orders orders) {
        ordersService.saveOrders(orders);
        return "redirect:/MenuViewBooking";
    }

    @GetMapping("/ordersC")
    public String getCompleteOrders(HttpSession session, Model model) {
        Long clientId = (Long) session.getAttribute("clientId");
        List<Orders> completeOrders = ordersService.getCompleteOrdersByClientId(clientId);
        model.addAttribute("orders", completeOrders);
        return "ordersC";
    }

    @GetMapping("/ordersCR")
    public String getRejectOrders(HttpSession session, Model model) {
        Long clientId = (Long) session.getAttribute("clientId");
        List<Orders> completeOrdersR = ordersService.getRejecteOrderSByClientId(clientId);
        model.addAttribute("ordersR", completeOrdersR);
        return "ordersC";
    }

    @GetMapping("/ordersCP")
    public String getPendingOrders(HttpSession session, Model model) {
        Long clientId = (Long) session.getAttribute("clientId");
        List<Orders> completeOrdersP = ordersService.getPendingOrderSbyClientId(clientId);
        model.addAttribute("ordersP", completeOrdersP);
        return "ordersC";
    }

}
