package com.server.tdMindJelly.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tdMindJelly.agedEmo.AgedEmo;
import com.server.tdMindJelly.jelly.Jelly;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : User
 * @description : User(사용자) Entity
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, length = 16)
    private String mobilePhoneNumber; //핸드폰 번호

    @Column(nullable = false, unique = true)
    private String email; //이메일

    @Column
    private String password; //비밀번호

    @Column
    private String userName; //실제 이름

    @Column
    private String nickName; //닉네임

    @Column
    private Boolean gender; // 성별

    @Column
    private String birthDate; //생일

    @Column
    private String profileImage; // 프로필 이미지

    @Column
    private int point; // 포인트

    @Column
    private String ageRange; //연령대

    @Column
    private Boolean isMarketing; //마케팅 정보 수신 동의

    @JsonIgnore
    @OneToMany(mappedBy = "jelly", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Jelly> jellyList =new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "agedEmo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AgedEmo> agedEmoList =new ArrayList<>();

    @Builder
    public User(String mobilePhoneNumber, String email, String password, String userName,
                String nickName, Boolean gender, String birthDate, String profileImage, int point,
                String ageRange, Boolean isMarketing, List<Jelly> jellyList, List<AgedEmo> agedEmoList) {
        this.mobilePhoneNumber = mobilePhoneNumber;
        this.email = email;
        this.password = password;
        this.userName = userName;
        this.nickName = nickName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.profileImage = profileImage;
        this.point = point;
        this.ageRange = ageRange;
        this.isMarketing = isMarketing;
        this.jellyList = jellyList != null ? jellyList : new ArrayList<>();
        this.agedEmoList = agedEmoList != null ? agedEmoList : new ArrayList<>();
    }

    public void updateUser(String password, String nickName,
                           String profileImage, Boolean isMarketing){
        this.password = (password != null)? password:this.password;
        this.nickName = (nickName != null)? nickName:this.nickName;
        this.profileImage = (profileImage != null)? profileImage:this.profileImage;
        this.isMarketing = (isMarketing != null)? isMarketing:this.isMarketing;
    }
}
