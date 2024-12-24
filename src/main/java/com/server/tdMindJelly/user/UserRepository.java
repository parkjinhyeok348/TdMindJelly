package com.server.tdMindJelly.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : UserRepository
 * @description : User(사용자)의 Repository
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
public interface UserRepository extends JpaRepository<User,Long> {
    // 중복 이메일이 있는지 조회
    User findByEmail(@Param("email") String email);

    // 중복 전화번호가 있는지 조회
    User findByMobilePhoneNumber(@Param("mobilePhoneNumber") String mobilePhoneNumber);

    // 중복 닉네임이 있는지 조회
    User findByNickName(@Param("nickName") String nickName);
}
