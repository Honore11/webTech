package web.webProject.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import web.webProject.Service.ClientrService;
import web.webProject.models.Client;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ClientController {
    @Autowired
    ClientrService clientrService;
    @GetMapping("/showNewUserForm")
    public String showUser(Model model){
        Client use=new Client();
        model.addAttribute("use", use);
        return "ClientSave";
    }
    @GetMapping("/showNewUserAdForm")
    public String showUserAd(Model model){
        Client use=new Client();
        model.addAttribute("use", use);
        return "AdSave";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("use") Client client){
        clientrService.saveUser(client);
        return "redirect:/showNewUserForm";

    }
    @PostMapping("/saveAdUser")
    public String saveAdUser(@ModelAttribute("use") Client client){
        clientrService.saveUser(client);
        return "redirect:/showNewUserAdForm";
    }


    @GetMapping("/userDashBoard")
    public String userDashBoard(Model model){
        List<Client> ListUsers= clientrService.listAllUsers();
        model.addAttribute("ListUsers",ListUsers);
        return "userDashBoard";
    }
    @GetMapping("/updateUser/{ClientId}")
    public String updateUser(@PathVariable(value = "ClientId") Long ClientId, Model model){
        Client user = clientrService.findbyId(ClientId);
        model.addAttribute("user", user);
        return "updateUser";
    }

    @GetMapping("/deleteUser/{ClientId}")
    public String deletePayment(@PathVariable(value = "ClientId") Long ClientId){
        this.clientrService.deleteClient(ClientId);
        return "redirect:/userDashBoard";
    }

    }
