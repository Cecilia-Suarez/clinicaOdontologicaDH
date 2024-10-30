package com.clinicaOdontologica.service;

import com.clinicaOdontologica.model.AppUser;
import com.clinicaOdontologica.model.AppUserRole;
import com.clinicaOdontologica.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception{
        BCryptPasswordEncoder passwordEncoder1 = new BCryptPasswordEncoder();
        String hashedPassword1 = passwordEncoder1.encode("password1");

        BCryptPasswordEncoder passwordEncoder2  =new BCryptPasswordEncoder();
        String hashedPassword2 = passwordEncoder2.encode("password2");

        userRepository.save(new AppUser("Administrador","Admin","admin@gmail.com", hashedPassword1, AppUserRole.ROLE_ADMIN));
        userRepository.save(new AppUser("Usuario", "User", "user@gmail.com", hashedPassword2, AppUserRole.ROLE_USER));
    }
}
