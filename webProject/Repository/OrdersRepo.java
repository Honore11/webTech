package web.webProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.webProject.models.Client;
import web.webProject.models.Estatus;
import web.webProject.models.Orders;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<Orders,Long> {

    List<Orders> findByClientAndEstatus(Client client, Estatus estatus);
}
