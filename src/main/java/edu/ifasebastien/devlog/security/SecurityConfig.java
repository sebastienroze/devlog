package edu.ifasebastien.devlog.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    DataSource datasource;

    @Autowired
    UserDetailsServiceCustom userDetailsService;

    @Autowired
    JwtRequestFilter jwtRequestFilter;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
/*
        auth.jdbcAuthentication()
                .dataSource(datasource)
                .usersByUsernameQuery("SELECT pseudo,password,1 FROM utilisateur WHERE pseudo=?")
                .authoritiesByUsernameQuery("SELECT pseudo,denomination FROM utilisateur " +
                        "JOIN utilisateur_role ON utilisateur.id = utilisateur_role.utilisateur_id "+
                        "JOIN role ON utilisateur.role_id = role.id "+
                        "WHERE pseudo=?")
                ;

*/
        /*

        auth.inMemoryAuthentication()
                .withUser("a")
                .password("q")
                .roles("USER")
        .and()
                .withUser("b")
                .password("a")
                .roles("ADMIN");
 */


    }
    @Bean
    public PasswordEncoder getPassword() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().configurationSource(httpServletRequest -> new CorsConfiguration().applyPermitDefaultValues() )
                .and().csrf().disable()
                .authorizeRequests()
                .antMatchers("/authentication").permitAll()
                .antMatchers("/admin/**").hasRole("ADMINISTRATEUR")
                .antMatchers("/user/**").hasAnyRole("ADMINISTRATEUR","UTILISATEUR")
                .antMatchers("/").permitAll()
        .anyRequest().authenticated()
        .and().exceptionHandling()
        .and().sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//                .and().formLogin()    ;
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
