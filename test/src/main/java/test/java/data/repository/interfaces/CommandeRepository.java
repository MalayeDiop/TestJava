package test.java.data.repository.interfaces;

import test.java.core.repo.Repository;
import test.java.data.entities.Commande;

public interface CommandeRepository extends Repository<Commande> {
     Commande selectById(int id) ;
     boolean update(Commande dette);

}
