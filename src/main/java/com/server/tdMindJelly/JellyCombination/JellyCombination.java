package com.server.tdMindJelly.JellyCombination;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tdMindJelly.agedEmo.AgedEmo;
import com.server.tdMindJelly.jelly.Jelly;
import com.server.tdMindJelly.jellyImage.JellyImage;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : JellyCombinaton
 * @description : JellyCombination 의 Entity
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name="jellyCombination")
public class JellyCombination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jellyCombId;

    @Column(nullable = false)
    private Long firstEmo; // 첫 번째 감정 id

    @Column(nullable = false)
    private Long secondEmo; // 두 번째 감정 id

    @Column
    private Boolean isAwaken; // 깨달음 여부

    @Column
    private String JellyIcon; // 아이콘 영문 이름

    @JsonIgnore
    @OneToMany(mappedBy = "jellyCombination", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Jelly> jellyList =new ArrayList<>(); // 젤리 조합에 속한 젤리 리스트

    @JsonIgnore
    @OneToMany(mappedBy = "jellyCombination", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<AgedEmo> agedEmoList =new ArrayList<>(); // 젤리 조합에 속한 숙성된 감정 리스트

    @Builder
    public JellyCombination(Long firstEmo, Long secondEmo, Boolean isAwaken,
                            String jellyIcon, List<Jelly> jellyList, List<AgedEmo> agedEmoList) {
        this.firstEmo = firstEmo;
        this.secondEmo = secondEmo;
        this.isAwaken = isAwaken;
        this.JellyIcon = jellyIcon;
        this.jellyList = jellyList != null ? jellyList : new ArrayList<>();
        this.agedEmoList = agedEmoList != null ? agedEmoList : new ArrayList<>();
    }
}
