package test.java.core.factory;

import test.java.service.list.ClientServiceImpl;
import test.java.service.list.CommandeServiceImpl;
import test.java.service.list.ProduitServiceImpl;

public class FactoryService {
    private static ClientServiceImpl clientService ;
    private static ProduitServiceImpl produitService;
    private static CommandeServiceImpl commandeService;

    FactoryRepositoryJpa factory = new FactoryRepositoryJpa();

    public ClientServiceImpl getInstanceClientService(){
        if (clientService==null) {
            return clientService=new ClientServiceImpl(factory.getInstanceRepoClient());
        }
        return clientService;
    }

    public ProduitServiceImpl getInstanceProduitService(){
        if (produitService==null) {
            return produitService=new ProduitServiceImpl(factory.getInstanceRepoProduit());
        }
        return produitService;
    }

    public CommandeServiceImpl getInstanceCommandeService(){
        if (commandeService==null) {
            return commandeService=new CommandeServiceImpl(factory.getInstanceRepoCommande());
        }
        return commandeService;
    }

}
