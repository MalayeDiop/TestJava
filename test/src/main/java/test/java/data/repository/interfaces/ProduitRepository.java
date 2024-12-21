package test.java.data.repository.interfaces;

import java.util.List;

import test.java.core.repo.Repository;
import test.java.data.entities.Produit;
import test.java.data.enums.EtatProduit;

public interface ProduitRepository extends Repository<Produit>{
    Produit selectById(int id) ;
    Produit selectBy(EtatProduit etat) ;
    List<Produit> selectByEtat(EtatProduit etat);
}
