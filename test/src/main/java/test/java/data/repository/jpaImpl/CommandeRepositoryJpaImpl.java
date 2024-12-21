package test.java.data.repository.jpaImpl;

import test.java.core.repo.RepositoryJPA;
import test.java.data.entities.Commande;
import test.java.data.repository.interfaces.CommandeRepository;

public class CommandeRepositoryJpaImpl extends RepositoryJPA<Commande> implements CommandeRepository {
    
    public CommandeRepositoryJpaImpl(Class<Commande> type) {
        super( type);
    }

    @Override
    public Commande selectById(int id) {
        Commande commande = null;
        try {
            commande = em.find(Commande.class, id);
        } catch (Exception e) {
            System.out.println("Erreur lors de la recherche de la commande par ID : " + e.getMessage());
        }
        return commande;
    }

    @Override
    public boolean update(Commande commande) {
        try {
            if (commande != null) {
                Commande existingCommande = em.find(Commande.class, commande.getId());
                if (existingCommande != null) {
                    em.getTransaction().begin();  
                    em.merge(commande);               
                    em.getTransaction().commit();  
                    return true;
                } else {
                    System.out.println("Aucune commande trouvée avec l'ID : " + commande.getId());
                    return false;
                }
            }
        } catch (Exception e) {
            em.getTransaction().rollback();  
            System.out.println("Erreur lors de la mise à jour de la commande : " + e.getMessage());
            return false;
        }
        return false;
    }
}
