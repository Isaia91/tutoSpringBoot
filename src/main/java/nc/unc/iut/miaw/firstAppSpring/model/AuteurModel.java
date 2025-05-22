package nc.unc.iut.miaw.firstAppSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "auteur")
public class AuteurModel {
    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;


    // Constructeurs
    public AuteurModel(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    // Constructeur par défaut
    public AuteurModel() {

    }

    // Getters et Setters
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    //Fin getters/

    public String toString() {
        return "Auteur{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                '}';
    }
}
