package nc.unc.iut.miaw.firstAppSpring.controller;

import nc.unc.iut.miaw.firstAppSpring.model.LivreModel;
import nc.unc.iut.miaw.firstAppSpring.repository.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/livres")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    //Page liste HTML
    @GetMapping("/list")
    public String getAllList(Model model) {
        List<LivreModel> livres = livreRepository.findAll();
        model.addAttribute("livres", livres);
        return "livres/list";
    }

    //Page détail HTML
    @GetMapping("/{id}")
    public String getLivreById(@PathVariable Long id, Model model) {
        LivreModel livre = livreRepository.findById(id).orElse(null);
        model.addAttribute("livre", livre);
        return "livres/detail";
    }

    //Supprimer et rediriger vers liste
    @GetMapping("/supprimer/{id}")
    public String supprimer(@PathVariable Long id) {
        livreRepository.deleteById(id);
        return "redirect:/livres/list";
    }

    // Optionnel : ajout d’un livre
    @GetMapping("/ajouter")
    public String ajouter() {
        LivreModel livre = new LivreModel("WADAWOM", "000000000000", 2023);
        livreRepository.save(livre);
        return "redirect:/livres/list";
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
                return "redirect:/livres/list";
            }
        }
        return "Aucun livre trouvé avec l'id " + id;
    }
}
