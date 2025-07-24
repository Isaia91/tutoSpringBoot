package nc.unc.iut.miaw.firstAppSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import nc.unc.iut.miaw.firstAppSpring.model.AuteurModel;
import nc.unc.iut.miaw.firstAppSpring.repository.AuteurRepository;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/auteurs")
public class AuteurController {

    @Autowired
    private AuteurRepository auteurRepository;

    // Page liste
    @GetMapping("/list")
    public String getAllList(Model model) {
        List<AuteurModel> auteurs = auteurRepository.findAll();
        model.addAttribute("auteurs", auteurs);
        return "auteurs/list";
    }

    // Page détail
    @GetMapping("/{id}")
    public String getAuteurById(@PathVariable Long id, Model model) {
        AuteurModel auteur = auteurRepository.findById(id).orElse(null);
        model.addAttribute("auteur", auteur);
        return "auteurs/detail";
    }

    // Supprimer (redirection vers liste)
    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        auteurRepository.deleteById(id);
        return "redirect:/auteurs/list";
    }

    @GetMapping("/ajouter")
    public String ajouterGet() {
        AuteurModel auteur = new AuteurModel("Bruce", "Raillard");
        auteurRepository.save(auteur);
        return "redirect:/auteurs/list";
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
            return "redirect:/auteurs/list";
        } else {
            return "Aucun auteur trouvé avec l'id " + id;
        }
    }
}
