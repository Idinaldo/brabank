package com.brabank.auth.adapters.in.controllers;

import com.brabank.auth.adapters.out.JpaIdentity;
import com.brabank.auth.adapters.out.persistance.IdentityJpaRepository;
import com.brabank.auth.domain.valueObjects.AccountStatus;
import com.brabank.auth.domain.valueObjects.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerLoginTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IdentityJpaRepository jpaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setup() {
        jpaRepository.deleteAll();

        JpaIdentity identity = new JpaIdentity();
        identity.setEmail("test@brabank.com");
        identity.setPasswordHash(passwordEncoder.encode("password123"));
        identity.setStatus(AccountStatus.ACTIVE);
        identity.setRole(Role.CLIENT);
        jpaRepository.save(identity);
    }

    @Test
    void shouldLoginSuccessfullyAndReturn200() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@brabank.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("test@brabank.com"))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.role").value("CLIENT"))
                .andExpect(jsonPath("$.status").value("ACTIVE"));
    }

    @Test
    void shouldReturn401ForWrongPassword() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@brabank.com\",\"password\":\"wrongpassword\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn401ForNonExistentEmail() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"nonexistent@brabank.com\",\"password\":\"password123\"}"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldReturn403ForBlockedAccount() throws Exception {
        jpaRepository.deleteAll();

        JpaIdentity blocked = new JpaIdentity();
        blocked.setEmail("blocked@brabank.com");
        blocked.setPasswordHash(passwordEncoder.encode("password123"));
        blocked.setStatus(AccountStatus.BLOCKED);
        blocked.setRole(Role.CLIENT);
        jpaRepository.save(blocked);

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"blocked@brabank.com\",\"password\":\"password123\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldReturn400ForMissingEmail() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"password\":\"password123\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldReturn400ForMissingPassword() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@brabank.com\"}"))
                .andExpect(status().isBadRequest());
    }
}
