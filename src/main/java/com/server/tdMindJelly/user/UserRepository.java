package com.server.tdMindJelly.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

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
public interface UserRepository extends JpaRepository<Users,Long> {
    // 중복 이메일이 있는지 조회
    Optional<Users> findByEmail(@Param("email") String email);

    // 중복 전화번호가 있는지 조회
    Optional<Users> findByMobilePhoneNumber(@Param("mobilePhoneNumber") String mobilePhoneNumber);

    // 이메일을 찾기 위해 사용자이름과 전화번호 조회
    Optional<Users> findByUserNameAndMobilePhoneNumber(String userName, String mobilePhoneNumber);

    // 임시 비밀 번호 발급을 위해 사용자 조회
    Optional<Users> findByUserNameAndEmailAndMobilePhoneNumber(String userName, String email, String mobilePhoneNumber);

    // 중복 닉네임이 있는지 조회
    Users findByNickName(@Param("nickName") String nickName);
}
