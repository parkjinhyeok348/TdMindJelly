package com.server.tdMindJelly.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.BadCredentialsException;

import com.server.tdMindJelly.user.DTO.UserLoginReqDTO;
import com.server.tdMindJelly.user.JWT.AuthenticatedUserService;
import com.server.tdMindJelly.user.JWT.JwtUtil;
import com.server.tdMindJelly.user.JWT.PasswordEncryptService;

class UserServiceLoginTest {

    private UserRepository userRepository;
    private PasswordEncryptService passwordEncryptService;
    private JwtUtil jwtUtil;
    private UserService userService;

    @BeforeEach
    void setUp() throws Exception {
        userRepository = mock(UserRepository.class);
        passwordEncryptService = new PasswordEncryptService();
        jwtUtil = new JwtUtil();
        setField(jwtUtil, "secret", "mtzWFI74Vh5zcQ8a3K/Bkwu3K9TXo30gdraLBospMH0=");
        jwtUtil.loadSecretKey();
        userService = new UserService(jwtUtil, userRepository, passwordEncryptService, mock(JavaMailSender.class), mock(AuthenticatedUserService.class));
    }

    @Test
    void loginReturnsJwtForMatchingEmailAndPassword() {
        String email = "user@example.com";
        String rawPassword = "password123";
        Users user = Users.builder()
                .email(email)
                .password(passwordEncryptService.encodePassword(rawPassword))
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        String token = userService.login(new UserLoginReqDTO(email, rawPassword));

        assertThat(token).isNotBlank();
        assertThat(jwtUtil.extractEmail(token)).isEqualTo(email);
        assertThat(jwtUtil.isTokenValid(token, email)).isTrue();
    }

    @Test
    void loginRejectsWrongPassword() {
        String email = "user@example.com";
        Users user = Users.builder()
                .email(email)
                .password(passwordEncryptService.encodePassword("correct-password"))
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        assertThatThrownBy(() -> userService.login(new UserLoginReqDTO(email, "wrong-password")))
                .isInstanceOf(BadCredentialsException.class);
    }

    private static void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
