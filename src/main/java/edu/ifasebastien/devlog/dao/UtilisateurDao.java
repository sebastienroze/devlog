package edu.ifasebastien.devlog.dao;

import edu.ifasebastien.devlog.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur,Integer> {
    public Utilisateur findByLogin(String login) ;
}
