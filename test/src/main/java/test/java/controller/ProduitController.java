package test.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import test.java.core.factory.FactoryService;
import test.java.data.entities.Produit;
import test.java.data.enums.EtatProduit;

public class ProduitController {
    @FXML private TextField libelleField;
    @FXML private TextField prixField;
    @FXML private TextField qteStockField;
    @FXML private TextArea outputArea;
    @FXML private Button createProduitButton;
    @FXML private EtatProduit etatProduit;

    private FactoryService factoryService = new FactoryService();

    @FXML
    private void initialize() {

    }

    @FXML
    private void createProduit() {
        String libelle = libelleField.getText();
        String prixString = prixField.getText();
        String qteStockString = qteStockField.getText();
        EtatProduit etatProduit = EtatProduit.Disponible;

        if (libelle.isEmpty() || prixString.isEmpty() || qteStockString.isEmpty() || etatProduit == null) {
            outputArea.appendText("Tous les champs doivent être remplis.\n");
            return;
        }

        int prix;
        double qteStock;
        try {
            prix = Integer.parseInt(prixString);
            qteStock = Double.parseDouble(qteStockString);
        } catch (NumberFormatException e) {
            outputArea.appendText("Le prix et la quantité doivent être des nombres valides.\n");
            return;
        }

        Produit produit = new Produit();
        produit.setLibelle(libelle);
        produit.setPrix(prix);
        produit.setQteStock(qteStock);

        if (produit.getQteStock()==0) {
            etatProduit = EtatProduit.Indisponible; 
        }
        produit.setEtatProduit(etatProduit);

        boolean success = enregistrerProduit(produit);

        if (success) {
            outputArea.appendText("Article créé avec succès.\n");
        } else {
            outputArea.appendText("Erreur lors de la création de l'article.\n");
        }

        clearFields();
    }

    private void clearFields() {
        libelleField.clear();
        prixField.clear();
        qteStockField.clear();
    }

    public boolean enregistrerProduit(Produit produit) {
        if (produit == null) {
            return false;
        }

        factoryService.getInstanceProduitService().save(produit);

        return true;
    }
}
