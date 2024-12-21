package test.java.service.interfaces;

import java.util.List;

import test.java.core.service.Service;
import test.java.data.entities.Produit;
import test.java.data.enums.EtatProduit;

public interface ProduitService extends Service<Produit> {
    Produit getBy(EtatProduit etat);
    List<Produit> getByProduitEtat(EtatProduit etat);
}
