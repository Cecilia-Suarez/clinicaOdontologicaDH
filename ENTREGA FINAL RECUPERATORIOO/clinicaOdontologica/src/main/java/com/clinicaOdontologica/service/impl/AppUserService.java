package com.clinicaOdontologica.service.impl;

import com.clinicaOdontologica.model.AppUser;
import com.clinicaOdontologica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService implements UserDetailsService {
    private IUserRepository userRepository;

    @Autowired
    public AppUserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> usuarioBuscado = userRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()){
            return usuarioBuscado.get();
        } else {
            throw new UsernameNotFoundException("Usuario/a con correo" + username + " no se encuentra en la BD");
        }
    }


}
