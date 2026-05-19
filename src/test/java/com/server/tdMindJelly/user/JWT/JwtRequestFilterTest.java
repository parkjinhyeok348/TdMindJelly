package com.server.tdMindJelly.user.JWT;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.server.tdMindJelly.user.UserRepository;
import com.server.tdMindJelly.user.Users;

class JwtRequestFilterTest {

    private JwtUtil jwtUtil;
    private UserRepository userRepository;
    private JwtRequestFilter jwtRequestFilter;

    @BeforeEach
    void setUp() throws Exception {
        SecurityContextHolder.clearContext();
        jwtUtil = new JwtUtil();
        setField(jwtUtil, "secret", "mtzWFI74Vh5zcQ8a3K/Bkwu3K9TXo30gdraLBospMH0=");
        jwtUtil.loadSecretKey();
        userRepository = mock(UserRepository.class);
        jwtRequestFilter = new JwtRequestFilter(jwtUtil, userRepository);
    }

    @AfterEach
    void tearDown() {
        SecurityContextHolder.clearContext();
    }

    @Test
    void bearerTokenAuthenticatesMatchingUser() throws Exception {
        String email = "user@example.com";
        String token = jwtUtil.generateToken(email, 1L);
        Users user = Users.builder()
                .email(email)
                .password("encoded-password")
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer " + token);

        jwtRequestFilter.doFilter(request, new MockHttpServletResponse(), new MockFilterChain());

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        assertThat(authentication).isNotNull();
        assertThat(authentication.getName()).isEqualTo(email);
        assertThat(authentication.getAuthorities()).extracting("authority").containsExactly("ROLE_USER");
    }

    @Test
    void invalidBearerTokenDoesNotAuthenticate() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        request.addHeader("Authorization", "Bearer invalid-token");

        jwtRequestFilter.doFilter(request, new MockHttpServletResponse(), new MockFilterChain());

        assertThat(SecurityContextHolder.getContext().getAuthentication()).isNull();
    }

    private static void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
