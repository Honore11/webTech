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
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @OneToOne
    private Orders orders;
    private String paymentDate;
    private String Amount;
    @Enumerated(EnumType.STRING)
    private Emethod emethod;
}
