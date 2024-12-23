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

    @Column
    private String email;



    @JsonIgnore
    @OneToMany(mappedBy = "jelly", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Jelly> jellyList =new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "agedEmo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AgedEmo> agedEmoList =new ArrayList<>();
}
