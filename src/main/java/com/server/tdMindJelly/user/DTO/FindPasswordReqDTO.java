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
 * @className : FindPasswordReqDTO
 * @description : 비밀번호 찾을 때 사용하는 Request DTO
 * @modification : 2024-12-30 (Jinhyeok)
 * @date : 2024-12-30
 */
@Getter
@NoArgsConstructor
public class FindPasswordReqDTO {
    private String email;
    private String userName;
    private String mobilePhoneNumber;

    @Builder
    public FindPasswordReqDTO(String email,String userName, String mobilePhoneNumber){
        this.email = email;
        this.userName = userName;
        this.mobilePhoneNumber =mobilePhoneNumber;
    }
}
