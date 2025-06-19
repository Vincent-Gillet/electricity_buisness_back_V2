package com.example.electricity_business_backend.config;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SecurityTests {
    @Autowired
    private MockMvc mockMvc;


/*    @Test
    public void getHomePage_shouldBePublic() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }*/

    @Test
    public void getUtilisateurs_shouldBePublic() throws Exception {
        mockMvc.perform(get("/api/utilisateurs"))
                .andExpect(status().isOk());
    }

    @Test
    public void getUtilisateurOne_shouldBePublic() throws Exception {
        mockMvc.perform(get("/api/utilisateurs/1"))
                .andExpect(status().isOk());
    }

/*    @Test
    public void getAdminPage_withoutAuth_shouldRedirectToLogin() throws Exception {
        mockMvc.perform(get("/api/bornes"))
                .andExpect(status().is3xxRedirection());
    }*/

    @Test
    public void getAdminPage_withoutAuth_shouldRedirectToLogin() throws Exception {
        mockMvc.perform(get("/api/bornes"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMINISTRATEUR"})
    public void getBornes_withAdminRole_shouldBeOk() throws Exception {
        mockMvc.perform(get("/api/bornes"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMINISTRATEUR"})
    public void getLieux_withAdminRole_shouldBeOk() throws Exception {
        mockMvc.perform(get("/api/lieux"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "utilisateur", roles = {"UTILISATEUR"})
    public void getBornes_withUserRole_shouldBeForbidden() throws Exception {
        mockMvc.perform(get("/api/bornes"))
                .andExpect(status().isForbidden());
    }


    @Test
    @WithMockUser(username = "admin", roles = {"ADMINISTRATEUR"})
    public void getAdminPage_withAdminRole_shouldBeNotFound() throws Exception {
        mockMvc.perform(get("/api/administrateurs"))
                .andExpect(status().isNotFound());
    }




}