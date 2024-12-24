package com.server.tdMindJelly.user.DTO;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-24     Jinhyeok      주석최초생성
 * @className : UserUpdateResDTO
 * @description : User 상세 정보를 수정할 때 사용하는 User Response DTO
 * @modification : 2024-12-24 (Jinhyeok)
 * @date : 2024-12-24
 */
@Getter
@NoArgsConstructor
public class UserUpdateResDTO {
    private String password;
    private String nickName;
    private String profileImage;
    private Boolean isMarketing;
    @Builder
    public UserUpdateResDTO(String password, String nickName, String profileImage, Boolean isMarketing) {
        this.password = password;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.isMarketing = isMarketing;
    }
}
