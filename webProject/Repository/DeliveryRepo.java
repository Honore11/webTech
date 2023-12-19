package web.webProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.webProject.models.Delivery;

@Repository
public interface DeliveryRepo extends JpaRepository<Delivery,Long> {
}
