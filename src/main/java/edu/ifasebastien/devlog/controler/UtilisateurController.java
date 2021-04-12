package edu.ifasebastien.devlog.controler;

import com.fasterxml.jackson.annotation.JsonView;
import edu.ifasebastien.devlog.dao.UtilisateurDao;
import edu.ifasebastien.devlog.model.Competence;
import edu.ifasebastien.devlog.model.Utilisateur;
import edu.ifasebastien.devlog.security.JwtUtil;
import edu.ifasebastien.devlog.security.UserDetailsServiceCustom;
import edu.ifasebastien.devlog.view.CustomJsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UtilisateurController {
    UtilisateurDao utilisateurDao;
    JwtUtil jwtUtil;
    AuthenticationManager authenticationManager;
    UserDetailsServiceCustom userDetailsServiceCustom;

    @Autowired
    UtilisateurController(
            UtilisateurDao utilisateurDao,
            JwtUtil jwtUtil,
            AuthenticationManager authenticationManager,
            UserDetailsServiceCustom userDetailsServiceCustom
    ) {
        this.utilisateurDao = utilisateurDao;
        this.jwtUtil = jwtUtil;
        this.authenticationManager =  authenticationManager;
        this. userDetailsServiceCustom = userDetailsServiceCustom;

    }
    @PostMapping("/authentication")
    public String authentication(@RequestBody Utilisateur utilisateur) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        utilisateur.getLogin(),utilisateur.getPassword()
                )
        );
        UserDetails userDetails = this.userDetailsServiceCustom.loadUserByUsername(utilisateur.getLogin());
        return jwtUtil.generateToken(userDetails);
    }

    @JsonView(CustomJsonView.VueUtilisateur.class)
    @GetMapping("/user/utilisateur/{id}")
    public Utilisateur getUtilisateur(@PathVariable int id) {
        return utilisateurDao.findById(id).orElse(null);
    }
    @JsonView(CustomJsonView.VueUtilisateur.class)
    @GetMapping("/user/utilisateurs")
    public List<Utilisateur> getUtilisateurs() {
//        return utilisateurDao.findAll();
        List<Utilisateur> listeUtilisateur = utilisateurDao.findAll();
        /*
        for (Utilisateur utilisateur : listeUtilisateur) {
            for (Competence competence : utilisateur.getListeCompetence()) {
                competence.setListeUtilisateur(null);
            }
        }
        */

        return listeUtilisateur;
    }


    @GetMapping("/user/utilisateur-by-login/{login}")
    public Utilisateur getUtilisateur(@PathVariable String login) {
        return utilisateurDao.findByLogin(login);
    }

    @PostMapping("/admin/utilisateur")
    public boolean addUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurDao.save(utilisateur);
        return true;
    }

    @DeleteMapping("/admin/utilisateur/{id}")
    public boolean deleteUtilisateur(@PathVariable int id) {
        utilisateurDao.deleteById(id);
        return true;
    }
}
