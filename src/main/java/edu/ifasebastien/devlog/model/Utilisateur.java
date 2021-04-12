package edu.ifasebastien.devlog.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import edu.ifasebastien.devlog.view.CustomJsonView;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({CustomJsonView.VueUtilisateur.class,CustomJsonView.VueCompetences.class})
    private int id;
    @Column(name="pseudo", unique=true,length = 30)
    @JsonView({CustomJsonView.VueUtilisateur.class,CustomJsonView.VueCompetences.class})
    private String login;
    private String password;

    @ManyToOne
    @JsonView({CustomJsonView.VueUtilisateur.class})
    private Statut statut;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonView({CustomJsonView.VueUtilisateur.class})
    @JoinTable(
            name="utilisateur_role",
            joinColumns = @JoinColumn(name= "utilisateur_id",referencedColumnName  ="id"),
            inverseJoinColumns = @JoinColumn(name="role_id",referencedColumnName  ="id")
    )

    private List<Role> listeRole;

    public List<Role> getListeRole() {
        return listeRole;
    }

    public void setListeRole(List<Role> role) {
        this.listeRole = role;
    }

    @ManyToMany
    @JsonView({CustomJsonView.VueUtilisateur.class})

    @JoinTable(
            name="utilisateur_competence",
            joinColumns = @JoinColumn(name= "utlisateur_id"),
            inverseJoinColumns = @JoinColumn(name="competence_id")
    )

    private List<Competence> listeCompetence;
    public List<Competence> getListeCompetence() {
        return listeCompetence;
    }

    public void setListeCompetence(List<Competence> listeCompetence) {
        this.listeCompetence = listeCompetence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

}
