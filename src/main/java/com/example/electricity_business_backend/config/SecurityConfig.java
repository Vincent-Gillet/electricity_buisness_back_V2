package com.example.electricity_business_backend.config;

import com.example.electricity_business_backend.config.filter.JwtAuthFilter;


import com.example.electricity_business_backend.repository.ReparateurRepository;
import com.example.electricity_business_backend.repository.UtilisateurRepository;
import com.example.electricity_business_backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
/*
@RequiredArgsConstructor
*/
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;

    private final CustomUserDetailService customUserDetailService;

/*    private final ReparateurRepository reparateurRepository;
    private final UtilisateurRepository utilisateurRepository;*/

    public SecurityConfig(JwtAuthFilter jwtAuthFilter, CustomUserDetailService customUserDetailService) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.customUserDetailService = customUserDetailService;
/*        this.reparateurRepository = reparateurRepository;
        this.utilisateurRepository = utilisateurRepository;*/
    }

    // PasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // UserDetailsService
/*    @Bean
    public UserDetailsService userDetailsService() {
        return mail -> (org.springframework.security.core.userdetails.UserDetails) utilisateurRepository.findByUtilisateurEmail(mail)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé : " + mail));
    }*/


    // AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

/*    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        auth -> auth.anyRequest().permitAll()
                )
                .httpBasic(httpBasic -> httpBasic.disable())
                .formLogin(formLogin -> formLogin.disable());

        return http.build();
    }*/

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
/*                .authorizeHttpRequests(authz -> authz
// 2. Définir les routes publiques
                                .requestMatchers("/api/auth/**").permitAll()
// 3. Le reste nécessite une authentification
                                .anyRequest().authenticated()
                )*/
                .authorizeHttpRequests(authz -> authz


                    // Les admins peuvent accéder à tout ce qui commence par /api/adminisatrateur
                                .requestMatchers(
                                        "/api/administrateurs/**",
                                        "/api/administrateurs",

                                        "/api/bornes/**"
/*
                                        "/api/utilisateurs"
*/


                    ).hasRole("ADMINISTRATEUR")


                    // Les reparateurs peuvent accéder à tout ce qui commence par /api/reparateurs/
                        .requestMatchers("/api/reparateur/**").hasRole("REPARATEUR")



                            // Les utilisateurs peuvent accéder à tout ce qui commence par /api/utilisateurs/
                            .requestMatchers("/api/profil/**").hasRole("UTILISATEUR")

                            // Les utilisateurs authentifiés peuvent voir les profils
                            .requestMatchers(
                                    "/profile.html",
                                    "/api/adresses/**",
                                        "/api/bornes/**",


                                    "/api/lieux/**",
                                    "/api/medias/**",
                                    "/api/reservations/**",
                                    "/api/services/**",
                                    "/api/vehicules/**"
/*
                                        "/api/utilisateurs/**"
*/


                            ).authenticated()


                            // Tout le monde peut voir la page d'accueil
                            .requestMatchers(
                                    "/",
                                    "/login",
                                    "/api/utilisateurs",
                                    "/api/utilisateurs/**",
                                    "/api/auth/**",
                                    "/api/auth"

                            ).permitAll()

                            // Le reste nécessite une authentification
                            .anyRequest().authenticated()
                )

/*
                .httpBasic(Customizer.withDefaults())
*/

/*
                .logout(logout -> logout.permitAll());
*/

                // 4. Définir la gestion de session comme STATELESS
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())// (Optionnel, mais bonne pratique)// 5. Ajouter notre filtre JWT avant le filtre standard
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(customUserDetailService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }


/*    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailService(utilisateurRepository, reparateurRepository);
    }*/


/*    @Bean
    public JwtAuthFilter jwtAuthFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        return new JwtAuthFilter(jwtService, userDetailsService);
    }*/



/*    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails utilisateur = User.builder()
                .username("utilisateur")
                .password(passwordEncoder().encode("password"))
                .roles("UTILISATEUR")
                .build();

        UserDetails reparateur = User.builder()
                .username("reparateur")
                .password(passwordEncoder().encode("reparateur"))
                .roles("REPARATEUR")
                .build();

        UserDetails administrateur = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin"))
                .roles("ADMINISTRATEUR", "UTILISATEUR")
                .build();

        return new InMemoryUserDetailsManager(utilisateur, reparateur, administrateur);

    }*/

}
