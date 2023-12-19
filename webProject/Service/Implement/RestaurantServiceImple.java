package web.webProject.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webProject.Repository.RestaurantRepo;
import web.webProject.Service.RestaurantService;
import web.webProject.models.Restaurant;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImple implements RestaurantService {

    RestaurantRepo restaurantRepo;
    @Autowired
    public RestaurantServiceImple(RestaurantRepo restaurantRepo){
        this.restaurantRepo=restaurantRepo;
    }

    @Override
    public void saveRestaurant(Restaurant restaurant) {
        this.restaurantRepo.save(restaurant);
    }

    @Override
    public List<Restaurant> listAllRestaurant() {
        return this.restaurantRepo.findAll();
    }

    @Override
    public Restaurant findRestobyId(Long restoId) {
        Optional<Restaurant>optional=restaurantRepo.findById(restoId);
        Restaurant restaurant=null;
        if (optional.isPresent()){
            restaurant=optional.get();
        }else {
            throw new RuntimeException("Restaurant is Not Found"+restoId);
        }
        return restaurant;
    }

    @Override
    public void deleteResto(Long restoId) {
        this.restaurantRepo.deleteById(restoId);
    }
}
