package web.webProject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.webProject.models.AdminLogin;
import web.webProject.models.Client;

@Repository
public interface AdminLoginRepo extends JpaRepository<AdminLogin,Long> {

    @Query("SELECT c FROM AdminLogin c WHERE c.username = :username")
    AdminLogin findByUsername(@Param("username") String username);


    AdminLogin findByUsernameAndPasswor(String username, String passwor);
}
