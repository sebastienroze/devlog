package edu.ifasebastien.devlog.model;


import com.fasterxml.jackson.annotation.JsonView;
import edu.ifasebastien.devlog.view.CustomJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Competence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CustomJsonView.VueUtilisateur.class,CustomJsonView.VueCompetences.class})
    private int id;
    private String nom;

    @JsonView({CustomJsonView.VueCompetences.class})
    @ManyToMany(mappedBy = "listeCompetence")
    private List<Utilisateur> listeUtilisateur;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String login) {
        this.nom = nom;
    }

    public List<Utilisateur> getListeUtilisateur() {
        return listeUtilisateur;
    }

    public void setListeUtilisateur(List<Utilisateur> listeUtilisateur) {
        this.listeUtilisateur = listeUtilisateur;
    }


}
