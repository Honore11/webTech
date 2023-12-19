package web.webProject.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Client {
    @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long  ClientId;
    @Column(name = "clientname")
    private String clientname;
    private String password;
    private String mail;
    private String phone_number;
    private String Addresses;

}
