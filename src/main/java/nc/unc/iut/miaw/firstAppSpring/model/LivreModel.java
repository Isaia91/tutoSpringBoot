package nc.unc.iut.miaw.firstAppSpring.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livre")
public class LivreModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String isbn;
    private int anneeParution;

    // Constructeur par défaut requis par JPA
    public LivreModel() {}

    public LivreModel(String titre, String isbn, int anneeParution) {
        this.titre = titre;
        this.isbn = isbn;
        this.anneeParution = anneeParution;
    }

    // Getters et setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getAnneeParution() {
        return anneeParution;
    }

    public void setAnneeParution(int anneeParution) {
        this.anneeParution = anneeParution;
    }
}
