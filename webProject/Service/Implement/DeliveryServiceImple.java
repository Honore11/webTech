package web.webProject.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webProject.Repository.DeliveryRepo;
import web.webProject.Service.DeliveryService;
import web.webProject.models.Delivery;

import java.util.List;
import java.util.Optional;

@Service
public class DeliveryServiceImple implements DeliveryService {

    DeliveryRepo deliveryRepo;
    @Autowired
    public DeliveryServiceImple(DeliveryRepo deliveryRepo){
        this.deliveryRepo=deliveryRepo;
    }
    @Override
    public void saveDelivery(Delivery delivery) {
      this.deliveryRepo.save(delivery);
    }

    @Override
    public List<Delivery> lsiDeliveries() {
        return this.deliveryRepo.findAll();
    }

    @Override
    public Delivery findDeliveryById(Long deliveryId) {
        Optional <Delivery> optional=deliveryRepo.findById(deliveryId);
        Delivery delivery=null;
        if (optional.isPresent()){
            delivery=optional.get();
        }else{
            throw new RuntimeException("Delivery Not found"+deliveryId);
        }
        return delivery;
    }

    @Override
    public void deleteDelivery(Long deliveryId) {
        this.deliveryRepo.deleteById(deliveryId);
    }
}
