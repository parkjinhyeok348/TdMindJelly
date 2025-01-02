package com.server.tdMindJelly.user.DTO;

import com.server.tdMindJelly.user.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : UserSaveReqDTO
 * @description : User 생성 요청할 때 사용 되는 User Request DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class UserSaveReqDTO {
    private String mobilePhoneNumber; // 전화번호
    private String email; // 이메일
    private String password; // 비밀번호
    private String userName; // 실명
    private String nickName; // 닉네임
    private Boolean gender; // 성별
    private String birthDate; // 생일날짜
    private String profileImage; // 프로필 이미지
    private String ageRange; // 연령대
    private Boolean isMarketing; // 마케팅 정보 수신 동의

    @Builder
    public UserSaveReqDTO(String mobilePhoneNumber, String email, String password, String userName,
                          String nickName, Boolean gender, String birthDate, String profileImage,
                          String ageRange, Boolean isMarketing)
    {
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.nickName = nickName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.profileImage = profileImage;
        this.ageRange = ageRange;
        this.isMarketing = isMarketing;
    }

    /**
     * UserSaveReqDTO를 User 엔티티로 변환하는 메서드
     */
    public Users toEntity(String encodePassword) {
        return Users.builder()
                .mobilePhoneNumber(mobilePhoneNumber)
                .email(email)
                .password(encodePassword)
                .userName(userName)
                .nickName(nickName)
                .gender(gender) // null 방지
                .birthDate(birthDate)
                .profileImage(profileImage)
                .point(0) // 신규 가입 시 기본 포인트 설정
                .ageRange(ageRange)
                .isMarketing(isMarketing)
                .build();
    }

}
