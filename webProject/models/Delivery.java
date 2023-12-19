package web.webProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long deliveryId;
    @ManyToOne
    @JoinColumn(name = "orderId", nullable = true)
    private Orders orders;
    private String deliveryDate;
    private String deliveryAddress;
    @Enumerated(EnumType.STRING)
    private Edelivery edelivery;
}
