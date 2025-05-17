package com.keygen.gymapi.service;

import com.keygen.gymapi.entity.User;
import com.keygen.gymapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomerDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User u = userRepo.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User yok: " + email));
        return org.springframework.security.core.userdetails.User
                .withUsername(u.getEmail())
                .password(u.getPasswordHash())
                .roles("USER")  // ileride roleId’ye göre özelleştirebilirsin
                .build();
    }
}
