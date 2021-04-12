package edu.ifasebastien.devlog.security;

import edu.ifasebastien.devlog.dao.UtilisateurDao;
import edu.ifasebastien.devlog.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceCustom implements UserDetailsService {
    @Autowired
    UtilisateurDao utilisateurDao;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurDao.findByLogin(s);
        return new UserDetailsCustom(utilisateur) ;
    }
}
