package edu.ifasebastien.devlog.controler;

import edu.ifasebastien.devlog.dao.StatutDao;
import edu.ifasebastien.devlog.model.Statut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin

public class StatutController {
    StatutDao statutDao;
    @Autowired
    StatutController(StatutDao statutDao) {
        this.statutDao = statutDao;
    }

    @GetMapping("/admin/statut/{id}")
    public Statut getStatut(@PathVariable int id) {
        return statutDao.findById(id).orElse(null);
    }

    @GetMapping("/admin/statuts")
    public List<Statut> getStatuts() {
        return statutDao.findAll();
    }
/*
    @GetMapping("/statut-by-login/{login}")
    public Statut getStatut(@PathVariable String login) {
        return statutDao.findByLogin(login);
    }
*/
    @PostMapping("/admin/statut")
    public boolean addStatut(@RequestBody Statut statut) {
        statutDao.save(statut);
        return true;
    }
    @DeleteMapping("/admin/statut/{id}")
    public boolean deleteStatut(@PathVariable int id) {
        statutDao.deleteById(id);
        return true;
    }
}
