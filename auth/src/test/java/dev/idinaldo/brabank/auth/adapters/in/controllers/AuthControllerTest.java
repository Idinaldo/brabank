package dev.idinaldo.brabank.auth.adapters.in.controllers;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import dev.idinaldo.brabank.auth.application.ports.out.GeneratedToken;
import dev.idinaldo.brabank.auth.application.ports.out.IdentityRepository;
import dev.idinaldo.brabank.auth.application.ports.out.TokenProvider;
import dev.idinaldo.brabank.auth.domain.models.Identity;
import dev.idinaldo.brabank.auth.domain.models.IdentityRole;
import dev.idinaldo.brabank.auth.domain.models.IdentityStatus;

@SpringBootTest
class AuthControllerTest {

    private static final String LOGIN_PATH = "/auth/login";

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    private TokenProvider tokenProvider;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @TestConfiguration
    static class TestBeans {

        @Bean
        @Primary
        IdentityRepository identityRepository() {
            return mock(IdentityRepository.class);
        }

        @Bean
        @Primary
        TokenProvider tokenProvider() {
            return mock(TokenProvider.class);
        }
    }

    @Test
    void shouldReturnAccessTokenWhenCredentialsAreValid() throws Exception {
        Identity identity = activeIdentity("usuario@brabank.com", passwordEncoder.encode("senhaDoUsuario"));

        when(identityRepository.findByEmail("usuario@brabank.com")).thenReturn(Optional.of(identity));
        when(tokenProvider.generateToken(identity)).thenReturn(new GeneratedToken("jwt-token", 3600));

        mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"usuario@brabank.com\",\"password\":\"senhaDoUsuario\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accessToken").value("jwt-token"))
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.expiresIn").value(3600));
    }

    @Test
    void shouldReturnBadRequestWhenEmailIsInvalid() throws Exception {
        mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"email-invalido\",\"password\":\"senhaDoUsuario\"}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Email must be valid."));
    }

    @Test
    void shouldReturnNotFoundWhenIdentityDoesNotExist() throws Exception {
        when(identityRepository.findByEmail("ausente@brabank.com")).thenReturn(Optional.empty());

        mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"ausente@brabank.com\",\"password\":\"senhaDoUsuario\"}"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Identity not found for email: ausente@brabank.com"));
    }

    @Test
    void shouldReturnUnauthorizedWhenPasswordIsInvalid() throws Exception {
        Identity identity = activeIdentity("usuario@brabank.com", passwordEncoder.encode("outraSenha"));

        when(identityRepository.findByEmail("usuario@brabank.com")).thenReturn(Optional.of(identity));

        mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"usuario@brabank.com\",\"password\":\"senhaDoUsuario\"}"))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.message").value("Invalid email or password."));
    }

    @Test
    void shouldReturnForbiddenWhenIdentityIsBlocked() throws Exception {
        Identity identity = identityWithStatus(IdentityStatus.BLOCKED);

        when(identityRepository.findByEmail("bloqueado@brabank.com")).thenReturn(Optional.of(identity));

        mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"bloqueado@brabank.com\",\"password\":\"senhaDoUsuario\"}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Identity is blocked."));
    }

    @Test
    void shouldReturnForbiddenWhenIdentityIsDeactivated() throws Exception {
        Identity identity = identityWithStatus(IdentityStatus.DEACTIVATED);

        when(identityRepository.findByEmail("desativado@brabank.com")).thenReturn(Optional.of(identity));

        mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"desativado@brabank.com\",\"password\":\"senhaDoUsuario\"}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Identity is deactivated."));
    }

    @Test
    void shouldReturnForbiddenWhenIdentityIsPendingVerification() throws Exception {
        Identity identity = identityWithStatus(IdentityStatus.PENDING_VERIFICATION);

        when(identityRepository.findByEmail("pendente@brabank.com")).thenReturn(Optional.of(identity));

        mockMvc.perform(post(LOGIN_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"pendente@brabank.com\",\"password\":\"senhaDoUsuario\"}"))
                .andExpect(status().isForbidden())
                .andExpect(jsonPath("$.message").value("Identity is pending verification."));
    }

    private Identity activeIdentity(String email, String passwordHash) {
        return Identity.builder()
                .id(UUID.randomUUID())
                .email(email)
                .passwordHash(passwordHash)
                .status(IdentityStatus.ACTIVE)
                .role(IdentityRole.CLIENT)
                .build();
    }

    private Identity identityWithStatus(IdentityStatus status) {
        return Identity.builder()
                .id(UUID.randomUUID())
                .email(status.name().toLowerCase() + "@brabank.com")
                .passwordHash(passwordEncoder.encode("senhaDoUsuario"))
                .status(status)
                .role(IdentityRole.CLIENT)
                .build();
    }
}