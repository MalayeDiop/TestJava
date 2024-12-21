package test.java.data.repository.interfaces;

import test.java.core.repo.Repository;
import test.java.data.entities.Client;

public interface ClientRepository extends Repository<Client>  {
    Client selectById(int id) ;
    Client selectByTel(String telephone);
}
