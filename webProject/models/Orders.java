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
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long order_Id;
    private String orderName;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    private Client client;
    private String orderDate;
    private String amount;
    @Enumerated(EnumType.STRING)
    private Estatus estatus;
}
