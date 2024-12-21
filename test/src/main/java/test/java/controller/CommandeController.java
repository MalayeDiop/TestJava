package test.java.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import test.java.core.factory.FactoryService;
import test.java.data.entities.Client;
import test.java.data.entities.Commande;
import test.java.data.entities.Details;
import test.java.data.entities.Produit;

public class CommandeController {
    private ObservableList<Details> addedProduits = FXCollections.observableArrayList();

    @FXML private ComboBox<Produit> produitComboBox;
    @FXML private TextField quantityField;
    @FXML private TableView<Details> tabview;
    @FXML private TableColumn<Details, String> libelleColumn;
    @FXML private TableColumn<Details, Integer> qteCommandeColumn;
    @FXML private TableColumn<Details, Double> montantColumn;
    @FXML private Button addProduitButton;
    @FXML private Button createCommandeButton;
    @FXML private TextArea outputArea;

    FactoryService factoryService =new FactoryService();

    public CommandeController() {

    }

    @FXML
    public void initialize() {
        produitComboBox.getItems().addAll(factoryService.getInstanceProduitService().show());

        libelleColumn.setCellValueFactory(cellData -> 
            new SimpleStringProperty(cellData.getValue().getProduit().getLibelle())
        );
        qteCommandeColumn.setCellValueFactory(new PropertyValueFactory<>("qteCommande"));
        montantColumn.setCellValueFactory(cellData ->
            new SimpleDoubleProperty(cellData.getValue().getProduit().getPrix() * cellData.getValue().getQteCommande()).asObject()
        );

        tabview.setItems(addedProduits);

        addProduitButton.setOnAction(e -> addProduitToTable());
        createCommandeButton.setOnAction(e -> createCommande());
    }

    @FXML   
    private void addProduitToTable() {
        Produit selectedProduit = produitComboBox.getValue();
        if (selectedProduit == null) {
            outputArea.appendText("Erreur : Aucun article sélectionné.\n");
            return;
        }

        String quantityText = quantityField.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityText);
            if (quantity <= 0) {
                outputArea.appendText("Erreur : La quantité doit être supérieure à 0.\n");
                return;
            } else if (quantity > selectedProduit.getQteStock()) {
                outputArea.appendText("Erreur : Quantité supérieure au stock disponible.\n");
                return;
            }
        } catch (NumberFormatException e) {
            outputArea.appendText("Erreur : Quantité invalide.\n");
            return;
        }

        Details existingDetail = null;
        for (Details detail : addedProduits) {
            if (detail.getProduit().equals(selectedProduit)) {
                existingDetail = detail;
                break;
            }
        }

        if (existingDetail != null) {
            existingDetail.setQteCommande(existingDetail.getQteCommande() + quantity);
            tabview.refresh();

        } else {
            Details detail = new Details();
            detail.setProduit(selectedProduit);
            detail.setQteCommande(quantity);
            addedProduits.add(detail);
        }

        produitComboBox.setValue(null);
        quantityField.clear();
    }

    @FXML   
    private void createCommande() {
        if (addedProduits.isEmpty()) {
            outputArea.appendText("Erreur : Aucun produit ajouté pour la commande.\n");
            return;
        }

        double montantTotal = addedProduits.stream()
                                           .mapToDouble(d -> d.getProduit().getPrix() * d.getQteDette())
                                           .sum();

        Commande commande = new Commande();
        commande.setClient();
        commande.setMontant(montantTotal);
        commande.setMontantVerse(0);
        commande.setMontantRestant(montantTotal);

        for (Details detail : addedProduits) {
            detail.setCommande(commande);
        }
        commande.setListeDetails(addedProduits);


        if (enregistrerCommande(commande)) {
            outputArea.appendText("Commande créée avec succès.\n");
            addedProduits.clear();
            tabview.getItems().clear();
        } else {
            outputArea.appendText("Erreur lors de la création de la commande.\n");
        }
    }
    public boolean enregistrerCommande(Commande commande) {
        if (commande == null) {
            return false;
        }
        factoryService.getInstanceCommandeService().save(commande);
        for (Details detail : commande.getListeDetails()) {
            Produit produit = detail.getProduit();
            produit.setQteStock(produit.getQteStock() - detail.getQteCommande());
            factoryService.getInstanceProduitService().update(produit);
        }
        return true;
    }
}
