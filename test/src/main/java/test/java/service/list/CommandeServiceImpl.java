package test.java.service.list;

import java.util.List;

import test.java.data.entities.Commande;
import test.java.data.repository.interfaces.CommandeRepository;
import test.java.service.interfaces.CommandeService;

public class CommandeServiceImpl implements CommandeService {
    CommandeRepository commandeRepository;

    public CommandeServiceImpl(CommandeRepository commandeRepository) {
        this.commandeRepository = commandeRepository;
    }

    @Override
    public boolean save(Commande object) {
        return commandeRepository.insert(object);
    }

    @Override
    public List<Commande> show() {
        return commandeRepository.selectAll();
    }

    @Override
    public boolean update(Commande object) {
        return commandeRepository.update(object);
    }

    @Override
    public void effacer(Commande object) {
        commandeRepository.remove(object);
    }

    @Override
    public Commande getById(int id) {
        return
            commandeRepository.selectAll().stream()
            .filter(client -> client.getId()==id)
            .findFirst()
            .orElse(null);
    }

    @Override
    public List<Commande> getByClientId(int clientId) {
        return commandeRepository.selectAll().stream()
                   .filter(dette -> dette.getClient().getId() == clientId)
                   .toList();
    }

    
}
