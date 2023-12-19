package web.webProject.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webProject.Repository.DeliveryRepo;
import web.webProject.Repository.MenuRepo;
import web.webProject.Service.DeliveryService;
import web.webProject.Service.MenuService;
import web.webProject.models.Menu;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImple implements MenuService {
    MenuRepo menuRepo;
    @Autowired
    public MenuServiceImple(MenuRepo menuRepo){
        this.menuRepo=menuRepo;
    }

    @Override
    public void saveMenu(Menu menu) {
      this.menuRepo.save(menu);
    }

    @Override
    public List<Menu> listAllMenus() {
        return this.menuRepo.findAll();
    }

    @Override
    public List<Menu> findByKeyword(String keyword) {
        return menuRepo.findByKeyword(keyword);
    }

    @Override
    public Menu findMenuById(Long menuId) {
        Optional<Menu>optional=menuRepo.findById(menuId);
        Menu menu=null;
        if (optional.isPresent()){
            menu=optional.get();
        }else {
            throw new RuntimeException("Menu is not Found"+menuId);
        }
        return menu;
    }

    @Override
    public void deleteMenu(Long menuId) {
      this.menuRepo.deleteById(menuId);
    }
}
