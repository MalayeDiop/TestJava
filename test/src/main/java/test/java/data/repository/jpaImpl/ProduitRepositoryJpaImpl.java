package test.java.data.repository.jpaImpl;

import java.util.List;

import jakarta.persistence.TypedQuery;
import test.java.core.repo.RepositoryJPA;
import test.java.data.entities.Produit;
import test.java.data.enums.EtatProduit;
import test.java.data.repository.interfaces.ProduitRepository;

public class ProduitRepositoryJpaImpl extends RepositoryJPA<Produit> implements ProduitRepository {
   
    public ProduitRepositoryJpaImpl(Class<Produit> type) {
        super(type);
    }

    @Override
    public Produit selectById(int id) {
        try {
            return em.find(Produit.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Produit selectBy(EtatProduit etat) {
        try {
            TypedQuery<Produit> query = em.createQuery(
                "SELECT a FROM Produit a WHERE a.etatProduit = :etat", Produit.class);
            query.setParameter("etat", etat);
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Produit> selectByEtat(EtatProduit etat) {
        try {
            TypedQuery<Produit> query = em.createQuery(
                "SELECT a FROM Produit a WHERE a.etatProduit = :etat", Produit.class);
            query.setParameter("etat", etat);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
