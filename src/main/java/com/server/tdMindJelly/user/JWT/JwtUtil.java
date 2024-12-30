package com.server.tdMindJelly.user.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-30     Jinhyeok      주석최초생성
 * @className : JwtUtil
 * @description : Jwt를 생성하고 검증하는 Utility
 * @modification : 2024-12-30 (Jinhyeok)
 * @date : 2024-12-30
 */
@Component
public class JwtUtil {
    private SecretKey SECRET_KEY;
    private static final String DEFAULT_SECRET_PATH = "config/secret.txt";
    // secret key 불러오기
    @PostConstruct
    public void loadSecretKey() {
        try {
            // 절대 경로 확인
            Path path = Paths.get(DEFAULT_SECRET_PATH);
            if (!Files.exists(path)) {
                // 파일이 없으면 리소스 경로로 대체
                path = Paths.get(new ClassPathResource(DEFAULT_SECRET_PATH).getURI());
            }

            String secret = Files.readString(path).trim(); // 파일 내용 읽고 공백 제거
            this.SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to load secret key", e);
        }
    }

    // JWT 생성
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10시간 유효
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT에서 이메일 추출
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    // JWT 유효성 검사
    public boolean isTokenValid(String token, String email) {
        return extractEmail(token).equals(email) && !isTokenExpired(token);
    }

    // JWT 만료 여부 확인
    private boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }
}
