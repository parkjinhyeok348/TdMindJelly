package com.server.tdMindJelly.user.DTO;

import com.server.tdMindJelly.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : UserUpdateReqDTO
 * @description : User 상세 정보를 수정할 때 사용하는 User Request DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class UserUpdateReqDTO {
    private String password;
    private String nickName;
    private String profileImage;
    private Boolean isMarketing;

    @Builder
    public UserUpdateReqDTO(String password, String nickName, String profileImage, Boolean isMarketing) {
        this.password = password;
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.isMarketing = isMarketing;
    }
}
