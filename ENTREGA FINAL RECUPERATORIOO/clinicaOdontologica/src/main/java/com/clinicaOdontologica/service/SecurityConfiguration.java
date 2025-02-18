package com.clinicaOdontologica.service;

import com.clinicaOdontologica.service.impl.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/turnos/new").hasAnyRole("ADMIN", "USER")
                .antMatchers("/odontologos/**", "/pacientes/**", "/turnos/**", "/admOdont.html", "/admPac.html", "/turnos.html").hasRole("ADMIN")
                .antMatchers("/turnosUser.html").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                    .successHandler((request, response, authentication) -> {
                        for (GrantedAuthority authority : authentication.getAuthorities()) {
                            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                                response.sendRedirect("/loggedAdm.html");
                                return;
                            } else if (authority.getAuthority().equals("ROLE_USER")) {
                                response.sendRedirect("/loggedUser.html");
                                return;
                            }
                        }
                    })
                .and()
                .logout();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }
}
