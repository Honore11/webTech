package web.webProject.Service.Implement;


import org.springframework.stereotype.Service;
import web.webProject.Repository.AdminLoginRepo;
import web.webProject.Service.AdminLoginService;
import web.webProject.models.AdminLogin;
import web.webProject.models.Client;

@Service
public class AdminServiceImple implements AdminLoginService {
  AdminLoginRepo adminLoginRepo;
  public  AdminServiceImple(AdminLoginRepo adminLoginRepo){
      this.adminLoginRepo=adminLoginRepo;
  }

    @Override
    public boolean authenticateAdmin(String username, String passwor) {
       AdminLogin adminLogin=adminLoginRepo.findByUsernameAndPasswor(username,passwor);

        return adminLogin != null;
    }

    @Override
    public AdminLogin getAdmin(String username) {
        return adminLoginRepo.findByUsername(username);
    }

}
