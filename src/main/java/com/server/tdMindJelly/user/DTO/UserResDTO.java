package com.server.tdMindJelly.user.DTO;

import com.server.tdMindJelly.agedEmo.AgedEmo;
import com.server.tdMindJelly.jelly.Jelly;
import com.server.tdMindJelly.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : UserResDTO
 * @description : User 데이터를 가져올 때 사용하는 User Response DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class UserResDTO {
    private Long userId;
    private String mobilePhoneNumber;
    private String email;
    private String password;
    private String userName;
    private String nickName;
    private Boolean gender;
    private String birthDate;
    private String profileImage;
    private int point;
    private String ageRange;
    private Boolean isMarketing;
    private List<Jelly> jellyList;
    private List<AgedEmo> agedEmoList;

    @Builder
    public UserResDTO(Users users) {
        this.userId = users.getUserId();
        this.mobilePhoneNumber = users.getMobilePhoneNumber();
        this.email = users.getEmail();
        this.password = users.getPassword();
        this.userName = users.getUserName();
        this.nickName = users.getNickName();
        this.gender = users.getGender();
        this.birthDate = users.getBirthDate();
        this.profileImage = users.getProfileImage();
        this.point = users.getPoint();
        this.ageRange = users.getAgeRange();
        this.isMarketing = users.getIsMarketing();
        this.jellyList = users.getJellyList();
        this.agedEmoList = users.getAgedEmoList();
    }
}
