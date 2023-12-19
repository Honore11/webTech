package web.webProject.Service;

import org.springframework.stereotype.Service;
import web.webProject.models.Client;


import java.util.List;

@Service
public interface ClientrService {
    void saveUser(Client client);
    List<Client>listAllUsers();
    Client findbyId(Long ClientId);
    void deleteClient(Long ClientId);

    boolean authenticateClient(String Clientname, String password);
    Client getClientClientname(String Clientname);
}
