package test.java.service.list;

import java.util.List;

import test.java.data.entities.Produit;
import test.java.data.enums.EtatProduit;
import test.java.data.repository.interfaces.ProduitRepository;
import test.java.service.interfaces.ProduitService;

public class ProduitServiceImpl implements ProduitService {
    ProduitRepository produitRepository;

    public ProduitServiceImpl(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @Override
    public boolean save(Produit object) {
        return produitRepository.insert(object);
    }

    @Override
    public List<Produit> show() {
        return produitRepository.selectAll();
    }

    @Override
    public boolean update(Produit produit) {
        Produit prod = getById(produit.getId());
    
        if (prod != null) {
            prod.setLibelle(produit.getLibelle());
            prod.setQteStock(produit.getQteStock());
            prod.setPrix(produit.getPrix());
            prod.setEtatProduit(produit.getEtatProduit());
    
            return produitRepository.update(prod);  
        }
        return false;
    }

    @Override
    public void effacer(Produit object) {
        produitRepository.remove(object);
    }

    @Override
    public Produit getById(int id) {
        return produitRepository.selectById(id);
    }

    @Override
    public Produit getBy(EtatProduit etat) {
        return produitRepository.selectBy(etat);
    }

    @Override
    public List<Produit> getByProduitEtat(EtatProduit etat) {
        return  produitRepository.selectByEtat(etat);
    }
}
