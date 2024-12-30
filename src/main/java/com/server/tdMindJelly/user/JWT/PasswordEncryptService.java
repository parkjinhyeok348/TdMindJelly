package com.server.tdMindJelly.user.JWT;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-30     Jinhyeok      주석최초생성
 * @className : PasswordEncryptService
 * @description : 사용자 비밀번호 암호화 Service
 * @modification : 2024-12-30 (Jinhyeok)
 * @date : 2024-12-30
 */
@Service
public class PasswordEncryptService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    public boolean matchesPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
