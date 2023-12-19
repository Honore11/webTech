package web.webProject.Service;

import web.webProject.models.Delivery;

import java.util.List;

public interface DeliveryService {
    void saveDelivery(Delivery delivery);
    List<Delivery>lsiDeliveries();
    Delivery findDeliveryById(Long deliveryId);
    void deleteDelivery(Long deliveryId);
}
