package edu.ifasebastien.devlog.controler;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ifasebastien.devlog.dao.CompetenceDao;
import edu.ifasebastien.devlog.model.Competence;
import edu.ifasebastien.devlog.model.Utilisateur;
import edu.ifasebastien.devlog.view.CustomJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CompetenceController {
    CompetenceDao competenceDao;
    @Autowired
    CompetenceController(CompetenceDao competenceDao) {
        this.competenceDao = competenceDao;
    }

    @GetMapping("/admin/competence/{id}")
    @JsonView(CustomJsonView.VueCompetences.class)

    public Competence getCompetence(@PathVariable int id) {
        return competenceDao.findById(id).orElse(null);
    }

    @GetMapping("/admin/competences")
    @JsonView(CustomJsonView.VueCompetences.class)
    public List<Competence> getCompetences() {
        List<Competence> listeCompetences =  competenceDao.findAll();
/*        for (Competence competence: listeCompetences)  {
            for (Utilisateur utilisateur : competence.getListeUtilisateur()) {
                utilisateur.setListeCompetence(null);
                utilisateur.getStatut().setListeUtilisateur(null);
            }
         }
 */
        return listeCompetences;

    }


    @PostMapping("/admin/competence")
    public boolean addCompetence(@RequestBody Competence competence) {
        competenceDao.save(competence);
        return true;
    }
    @DeleteMapping("/admin/competence/{id}")
    public boolean deleteCompetence(@PathVariable int id) {
        competenceDao.deleteById(id);
        return true;
    }
}
