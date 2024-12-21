package test.java.data.repository.jpaImpl;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import test.java.core.factory.FactoryRepositoryJpa;
import test.java.core.repo.RepositoryJPA;
import test.java.data.entities.Client;
import test.java.data.repository.interfaces.ClientRepository;

public class ClientRepositoryJpaImpl extends RepositoryJPA<Client> implements ClientRepository {

    FactoryRepositoryJpa factory = new FactoryRepositoryJpa();

    public ClientRepositoryJpaImpl(Class<Client> type) {
        super(type);
    }

    @Override
    public Client selectById(int id) {
        Client client = null;
        try {
            client = em.find(Client.class, id); 
            if (client == null) {
                System.out.println("Aucun client trouvé avec l'ID : " + id);
            }
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche du client par ID : " + e.getMessage());
        }
        return client;
    }

    @Override
    public Client selectByTel(String telephone) {
        Client client = null;
        try {
            TypedQuery<Client> query = em.createQuery(
                String.format("SELECT c FROM Client c WHERE c.telephone = :telephone"),
                Client.class);
            query.setParameter("telephone", telephone); 
            client = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Aucun client trouvé avec le numéro : " + telephone);
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche du client par numéro : " + e.getMessage());
        }
        return client;
    }
    
}
