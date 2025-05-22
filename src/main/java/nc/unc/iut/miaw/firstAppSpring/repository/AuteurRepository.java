package nc.unc.iut.miaw.firstAppSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import nc.unc.iut.miaw.firstAppSpring.model.AuteurModel;


public interface AuteurRepository  extends JpaRepository<AuteurModel, Long> {
    Object prenom(String prenom);
    // Cette interface hérite de JpaRepository, ce qui lui permet d'accéder à toutes les méthodes CRUD
    // sans avoir besoin de les implémenter.
    // Spring Data JPA va automatiquement générer l'implémentation de cette interface à l'exécution.
    // Il n'est pas nécessaire d'ajouter des annotations comme @Repository ici.

}
