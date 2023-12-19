package web.webProject.Service;

import web.webProject.models.Menu;

import java.util.List;

public interface MenuService {
    void saveMenu(Menu menu);
    List<Menu>listAllMenus();
    List<Menu>findByKeyword(String keyword);
    Menu findMenuById(Long menuId);
    void deleteMenu(Long menuId);
}
