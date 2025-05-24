package nc.unc.iut.miaw.firstAppSpring.controller;

import nc.unc.iut.miaw.firstAppSpring.model.LivreModel;
import nc.unc.iut.miaw.firstAppSpring.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping
    public List<LivreModel> getAll() {
        return livreRepository.findAll();
    }

    @GetMapping("/list")
    public String getAllList() {
        List<LivreModel> livres = livreRepository.findAll();
        if (livres.isEmpty()) {
            return "Aucun livre enregistré.";
        }

        StringBuilder sb = new StringBuilder("Liste des livres :\n\n");
        for (LivreModel livre : livres) {
            sb.append("- ")
                    .append(livre.getTitre()).append(" (id: ").append(livre.getId()).append(")\n");
        }
        return sb.toString();
    }

    @GetMapping("/ajouter")
    public String ajouterGet() {
        LivreModel livre = new LivreModel("WADAWOM", "000000000000", 2023);
        livreRepository.save(livre);
        return "Livre ajouté : " + livre.getTitre();
    }

    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        if (livreRepository.existsById(id)) {
            livreRepository.deleteById(id);
            return "Livre supprimé.";
        } else {
            return "Aucun livre trouvé avec l'id " + id;
        }
    }
    @GetMapping("/modifier/{id}/{titre}/{isbn}/{anneeParution}")
    public String modifier(@PathVariable Long id,
                           @PathVariable String titre,
                           @PathVariable String isbn,
                           @PathVariable int anneeParution) {
        if (livreRepository.existsById(id)) {
            LivreModel livre = livreRepository.findById(id).orElse(null);
            if (livre != null) {
                livre.setTitre(titre);
                livre.setIsbn(isbn);
                livre.setAnneeParution(anneeParution);
                livreRepository.save(livre);
                return "Livre modifié : " + livre.getTitre();
            }
        }
        return "Aucun livre trouvé avec l'id " + id;
    }

    @GetMapping("{id}")
    public String getLivreById(@PathVariable Long id) {
        if (livreRepository.existsById(id)) {
            LivreModel livre = livreRepository.findById(id).orElse(null);
            if (livre != null) {
                return "Livre trouvé : " + livre.getTitre();
            }
        }
        return "Aucun livre trouvé avec l'id " + id;
    }
}
