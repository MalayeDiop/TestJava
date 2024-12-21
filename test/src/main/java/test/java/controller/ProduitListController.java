package test.java.controller;

import java.io.IOException;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import test.java.core.factory.FactoryService;
import test.java.data.entities.Produit;
import test.java.data.enums.EtatProduit;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;

public class ProduitListController {
    @FXML private TableView<Produit>tabview ;
    @FXML private TableColumn<Produit, String> libelle;
    @FXML private TableColumn<Produit, String> qteStock;
    @FXML private TableColumn<Produit, String> prix;
    @FXML private TableColumn<Produit, EtatProduit> etatProduit;

    
    private FactoryService factoryService;

    public void initialize(){
        libelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        qteStock.setCellValueFactory(new PropertyValueFactory<>("qteStock"));
        prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
        etatProduit.setCellValueFactory(new PropertyValueFactory<>("etatProduit"));
        factoryService= new FactoryService();
        loadTable();
    }

    public void loadTable(){
        List<Produit> produit = factoryService.getInstanceProduitService().show();
        System.out.println(produit);        
        ObservableList<Produit>listProduit=FXCollections.observableArrayList(produit);
        tabview.setItems(listProduit);
    };

    @FXML
    public void addProduit() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/test/java/produitForm.fxml"));
            Parent newContent = loader.load();

            Parent parentRoot = tabview.getScene().getRoot();
            VBox contentPane = (VBox) parentRoot.lookup("#contentPane");

            if (contentPane != null) {
                contentPane.getChildren().setAll(newContent);
            } else {
                System.out.println("ContentPane non trouvé !");
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Erreur lors du chargement de la vue produitForm.fxml");
        }
    }
}
