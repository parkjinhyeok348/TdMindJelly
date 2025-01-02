package com.server.tdMindJelly.user.DTO;

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
    private String nickName; // 닉네임
    private String profileImage; // 프로필 이미지
    private Boolean isMarketing; // 마켓팅 정보 수신 동의

    @Builder
    public UserUpdateReqDTO( String nickName, String profileImage, Boolean isMarketing) {
        this.nickName = nickName;
        this.profileImage = profileImage;
        this.isMarketing = isMarketing;
    }
}
