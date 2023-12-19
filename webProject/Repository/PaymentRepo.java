package web.webProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.webProject.models.Payment;

@Repository
public interface PaymentRepo extends JpaRepository<Payment,Long> {
}
