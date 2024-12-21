package test.java.controller;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.function.Consumer;
import test.java.core.factory.FactoryService;
import test.java.data.entities.Client;

public class ClientController {
    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField telephoneField;
    @FXML private TextField adresseField;
    @FXML private TextArea outputArea;
    @FXML private Button createClientButton;
    @FXML private CheckBox associateUserCheckBox;

    private Consumer<Client> onClientCreated;
    private FactoryService factoryService = new FactoryService();

    @FXML
    private void initialize() {
        
    }

    @FXML
    private void createClient() {
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String telephone = telephoneField.getText();
        String adresse = adresseField.getText();
    
        if (nom.isEmpty() || prenom.isEmpty() || telephone.isEmpty() || adresse.isEmpty()) {
            outputArea.appendText("Tous les champs du client doivent être remplis.\n");
            return;
        }
    
        Client client = new Client();
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setTelephone(telephone);
        client.setAddress(adresse);
    
        boolean success = enregistrerClient(client);
            
        if (onClientCreated != null) {
            onClientCreated.accept(client);
        }    
        if (success) {
            outputArea.appendText("Client et utilisateur créés avec succès.\n");
        } else {
            outputArea.appendText("Erreur lors de la création du client ou de l'utilisateur.\n");
        }
        clearFields();
    }

    private void clearFields() {
        nomField.clear();
        prenomField.clear();
        telephoneField.clear();
        adresseField.clear();
        associateUserCheckBox.setSelected(false);

    }

    public boolean enregistrerClient(Client client) {
        if (client == null) {
            return false; 
        }
        factoryService.getInstanceClientService().save(client);
        return true; 
    }
    

    public void setOnClientCreated(Consumer<Client> onClientCreated) {
        this.onClientCreated = onClientCreated;
    }
}
