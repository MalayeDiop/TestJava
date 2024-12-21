package test.java.core.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import test.java.data.entities.Client;
import test.java.data.entities.Commande;
import test.java.data.entities.Details;
import test.java.data.entities.Produit;
import test.java.data.repository.interfaces.ClientRepository;
import test.java.data.repository.interfaces.CommandeRepository;
import test.java.data.repository.interfaces.DetailsRepository;
import test.java.data.repository.interfaces.ProduitRepository;
import test.java.data.repository.jpaImpl.ClientRepositoryJpaImpl;
import test.java.data.repository.jpaImpl.CommandeRepositoryJpaImpl;
import test.java.data.repository.jpaImpl.DatailsRepositoryJpaImpl;
import test.java.data.repository.jpaImpl.ProduitRepositoryJpaImpl;

public class FactoryRepositoryJpa {
    private static ClientRepository clientRepository;
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("POSTGRESQLDETTES");
    private static EntityManager em;
    private static ProduitRepository produitRepository;
    private static CommandeRepository commandeRepository;
    private static DetailsRepository detailsRepository;

    public ClientRepository getInstanceRepoClient(){
        if (clientRepository==null) {
             em = emf.createEntityManager();
            clientRepository = new ClientRepositoryJpaImpl(Client.class);
        }
        return clientRepository;
    }

    public ProduitRepository getInstanceRepoProduit(){
        if (produitRepository == null) {
            em=emf.createEntityManager();
            produitRepository = new ProduitRepositoryJpaImpl(Produit.class);
        }
        return produitRepository;
    }  

    public CommandeRepository getInstanceRepoCommande(){
        if (commandeRepository==null) {
            em=emf.createEntityManager();
            commandeRepository=new CommandeRepositoryJpaImpl(Commande.class);    
            }
        return commandeRepository;
    } 

    public DetailsRepository getInstanceRepoDetails(){
        if (detailsRepository==null) {
            em=emf.createEntityManager();
            detailsRepository =new DatailsRepositoryJpaImpl(Details.class);        
        }
        return detailsRepository;
    }  
}
