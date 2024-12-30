package com.server.tdMindJelly.user.JWT;

import com.server.tdMindJelly.user.User;
import com.server.tdMindJelly.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

/**
 * @author : KTDS
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-30     KTDS      주석최초생성
 * @className : JwtRequestFilter
 * @description :
 * @modification : 2024-12-30 (KTDS)
 * @date : 2024-12-30
 */
@Component
@RequiredArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            email = jwtUtil.extractEmail(jwt);
        }

        // 사용자 인증 처리
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> userOptional = userRepository.findByEmail(email);
            if (userOptional.isPresent() && jwtUtil.isTokenValid(jwt, userOptional.get().getEmail())) {
                UserDetails userDetails = new CustomUserDetails(userOptional.get());
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

        }

        chain.doFilter(request, response);
    }
}
