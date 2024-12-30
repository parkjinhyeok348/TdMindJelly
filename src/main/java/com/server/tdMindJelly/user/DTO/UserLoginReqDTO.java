package com.server.tdMindJelly.user.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-30     Jinhyeok      주석최초생성
 * @className : UserLoginReqDTO
 * @description : 로그인에 사용될 User Request DTO
 * @modification : 2024-12-30 (Jinhyeok)
 * @date : 2024-12-30
 */
@Getter
@NoArgsConstructor
public class UserLoginReqDTO {
    private String email;
    private String password;

    @Builder
    public UserLoginReqDTO(String email,String password) {
        this.email = email;
        this.password = password;
    }
}
