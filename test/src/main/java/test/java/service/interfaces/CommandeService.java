package test.java.service.interfaces;

import java.util.List;

import test.java.core.service.Service;
import test.java.data.entities.Commande;

public interface CommandeService extends Service<Commande> {
    List<Commande> getByClientId(int clientId);
}
