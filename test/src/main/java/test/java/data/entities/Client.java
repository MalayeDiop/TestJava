package test.java.data.entities;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false,of = {"telephone"})
@Entity
@Table(name="client")

public class Client extends AbstractEntity implements Identifiable {

    @Column(length = 20,unique = true)
    private String prenom;

    @Column(length = 20,unique = true)
    private String nom;

    @Column(length = 9,unique = true)
    private String telephone;

    @Column(length = 255,unique = true)
    private String address;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Commande> listeCommandes = new ArrayList<>();


    public void setListeCommande(Commande commande) {
        commande.setClient(this); 
        listeCommandes.add(commande);
    }


    @Override
    public String toString() {
        return "Client [" +"id="+ id + ",prenom=" + prenom + ", nom=" + nom + ", telephone=" + telephone + ", address=" + address
                +"]";
    }
    

}
