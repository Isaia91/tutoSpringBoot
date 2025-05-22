package nc.unc.iut.miaw.firstAppSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import nc.unc.iut.miaw.firstAppSpring.model.AuteurModel;
import nc.unc.iut.miaw.firstAppSpring.repository.AuteurRepository;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/auteurs")
public class AuteurController {

    @Autowired
    private AuteurRepository auteurRepository;

    @GetMapping
    public List<AuteurModel> getAll() {
        return auteurRepository.findAll();
    }

    @GetMapping("/list")
    public String getAllList() {
        List<AuteurModel> auteurs = auteurRepository.findAll();
        if (auteurs.isEmpty()) {
            return "Aucun auteur enregistré.";
        }

        StringBuilder sb = new StringBuilder("Liste des auteurs :\n\n");
        for (AuteurModel auteur : auteurs) {
            sb.append("- ")
                    .append(auteur.getPrenom()).append(" ").append(auteur.getNom())
                    .append(" (id: ").append(auteur.getId()).append(")\n");
        }
        return sb.toString();
    }

    @GetMapping("/ajouter")
    public String ajouterGet() {
        AuteurModel auteur = new AuteurModel("To", "Delete");
        auteurRepository.save(auteur);
        return "Auteur ajouté : " + auteur.getPrenom() + " " + auteur.getNom();
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        if (auteurRepository.existsById(id)) {
            auteurRepository.findById(id).orElse(null);
            String prenom = Objects.requireNonNull(auteurRepository.findById(id).orElse(null)).getPrenom();
            String nom = Objects.requireNonNull(auteurRepository.findById(id).orElse(null)).getNom();
            auteurRepository.deleteById(id);
            return "Auteur "+ prenom + " "+ nom + " supprimé.";
        } else {
            return "Aucun auteur trouvé avec l'id " + id;
        }
    }

    @GetMapping("/modifier/{id}/{nom}/{prenom}")
    public String modifier(@PathVariable Long id,
                           @PathVariable String nom,
                           @PathVariable String prenom) {
        AuteurModel auteur = auteurRepository.findById(id).orElse(null);
        if (auteur != null) {
            auteur.setNom(nom);
            auteur.setPrenom(prenom);
            auteurRepository.save(auteur);
            return "Auteur modifié "+"(id: " + id + ") "+" : " + prenom + " " + nom ;
        } else {
            return "Aucun auteur trouvé avec l'id " + id;
        }
    }

    @GetMapping("/{id}")
    public String getAuteurById(@PathVariable Long id) {
        AuteurModel auteur = auteurRepository.findById(id).orElse(null);
        if (auteur != null) {
            return auteur.getPrenom() + " " + auteur.getNom() + " (id: " + id + ")";
        } else {
            return "Aucun auteur trouvé avec l'id " + id;
        }
    }
}
