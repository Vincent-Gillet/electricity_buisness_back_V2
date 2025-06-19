package com.example.electricity_business_backend.controller;

import com.example.electricity_business_backend.config.CustomUserDetailService;
import com.example.electricity_business_backend.dto.UtilisateurCreateDTO;
import com.example.electricity_business_backend.model.User;
import com.example.electricity_business_backend.model.Utilisateur;
import com.example.electricity_business_backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomUserDetailService customUserDetailService;// Ou un service dédié// ... injecter les dépendances ...

    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthRequest request) {
// On authentifie l'utilisateur avec le manager de Spring Security
/*        try {*/

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.utilisateurEmail(), request.utilisateurMotDePasse())
        );

// Si l'authentification réussit, on génère un token// Pas besoin de recharger l'utilisateur, mais c'est une option
        final UserDetails userDetails = customUserDetailService.loadUserByUsername(request.utilisateurEmail());
        final String jwt = jwtService.generateToken(userDetails.getUsername());

        return ResponseEntity.ok(jwt);

/*            } catch (Exception e) {
            return ResponseEntity.status(401).body("Authentication failed: " + e.getMessage());
        }*/
    }

    record AuthRequest(String utilisateurEmail, String utilisateurMotDePasse) {}

}
