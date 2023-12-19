package web.webProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderitemId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Orders orders;
    @ManyToOne
    @JoinColumn(name = "menuId", nullable = true)
    private Menu menu;
    private String quality;
    private String subtotal;
}
