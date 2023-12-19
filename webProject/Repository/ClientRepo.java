package web.webProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.webProject.models.Client;


import java.util.List;

@Repository
public interface ClientRepo extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Client c WHERE c.clientname = :clientname")
    Client findByClientname(@Param("clientname") String clientname);


    Client findByClientnameAndPassword(String clientname, String password);

}
