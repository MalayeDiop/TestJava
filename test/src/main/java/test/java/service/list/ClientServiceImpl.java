package test.java.service.list;

import java.util.List;

import test.java.data.entities.Client;
import test.java.data.repository.interfaces.ClientRepository;
import test.java.service.interfaces.ClientService;

public class ClientServiceImpl implements ClientService {
    ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public boolean save(Client object) {
        return clientRepository.insert(object);
    }

    @Override
    public List<Client> show() {
        return clientRepository.selectAll();
    }

    @Override
    public boolean update(Client object) {
        return clientRepository.update(object);
    }

    @Override
    public void effacer(Client object) {
        clientRepository.remove(object);
    }

    @Override
    public Client getById(int id) {
        return
        clientRepository.selectById(id);
    }

    @Override
    public Client getByTel(String telephone) {
        return
        clientRepository.selectByTel(telephone);
    }
}
