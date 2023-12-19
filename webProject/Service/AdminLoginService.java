package web.webProject.Service;

import org.springframework.stereotype.Service;
import web.webProject.models.AdminLogin;
import web.webProject.models.Client;


public interface AdminLoginService {
    boolean authenticateAdmin(String username, String passwor);
    AdminLogin getAdmin(String username);
}
