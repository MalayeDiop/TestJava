package test.java.service.interfaces;

import test.java.core.service.Service;
import test.java.data.entities.Client;

public interface ClientService extends Service<Client> {
    Client getByTel(String telephone);
}
