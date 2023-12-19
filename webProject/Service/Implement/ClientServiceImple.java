package web.webProject.Service.Implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.webProject.Repository.ClientRepo;
import web.webProject.Service.ClientrService;
import web.webProject.models.Client;


import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImple implements ClientrService {
    private final ClientRepo clientRepo;
    @Autowired
    public ClientServiceImple(ClientRepo clientRepo) {
        this.clientRepo = clientRepo;
    }
    @Override
    public void saveUser(Client client) {
      this.clientRepo.save(client);
    }

    @Override
    public List<Client> listAllUsers() {
        return this.clientRepo.findAll();
    }

    @Override
    public Client findbyId(Long ClientId) {
        Optional<Client> optional = clientRepo.findById(ClientId);
        Client client = null;
        if (optional.isPresent()) {
            client = optional.get();
        } else {
            throw new RuntimeException("Restaurant is Not Found" + ClientId);
        }
        return client;
    }

    @Override
    public void deleteClient(Long ClientId) {
       this.clientRepo.deleteById(ClientId);
    }


    @Override
    public boolean authenticateClient(String Clientname, String password) {
        Client client = clientRepo.findByClientnameAndPassword(Clientname,password);
        return  client != null;
    }


    @Override
    public Client getClientClientname(String Clientname) {
        return clientRepo.findByClientname(Clientname);
    }
}
