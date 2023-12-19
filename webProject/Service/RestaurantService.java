package web.webProject.Service;

import web.webProject.models.Restaurant;

import java.util.List;

public interface RestaurantService {
    void saveRestaurant(Restaurant restaurant);
    List<Restaurant>listAllRestaurant();
    Restaurant findRestobyId(Long restoId);
    void deleteResto(Long restoId);
}
