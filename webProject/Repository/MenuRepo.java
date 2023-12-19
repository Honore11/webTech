package web.webProject.Repository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import web.webProject.models.Menu;

import java.util.List;

@Repository
public interface MenuRepo extends JpaRepository<Menu,Long> {

    @Query(value = "select * from Menu me where me.itemName like %:keyword% or me.price like %:keyword%" +
            "or me.restaurant.restoName like %:keyword%",nativeQuery = true)
    List<Menu>findByKeyword(@Param("keyword") String keyword);
}
