package com.example.electricity_business_backend.config;

import com.example.electricity_business_backend.repository.ReparateurRepository;
import com.example.electricity_business_backend.repository.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UtilisateurRepository utilisateurRepository;
    private final ReparateurRepository reparateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        return utilisateurRepository.findByUtilisateurEmail(username)
                .map(user -> (UserDetails) user)
                .or(() -> reparateurRepository.findByEmailReparateur(username).map(reparateur -> (UserDetails) reparateur))
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + username));


    }


}
