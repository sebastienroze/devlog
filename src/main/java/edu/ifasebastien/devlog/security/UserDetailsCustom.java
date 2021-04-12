package edu.ifasebastien.devlog.security;

import edu.ifasebastien.devlog.model.Role;
import edu.ifasebastien.devlog.model.Utilisateur;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsCustom implements UserDetails {
    private String userName;
    private String password;
    private boolean active;
    private List<GrantedAuthority> authrities;

    public UserDetailsCustom(Utilisateur utilisateur) {
        this.userName=utilisateur.getLogin();
        this.password=utilisateur.getPassword();

        active = true;

        authrities = new ArrayList<>();
        for (Role role : utilisateur.getListeRole()) {
            authrities.add(new SimpleGrantedAuthority(role.getDenomination()));
        }
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authrities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
