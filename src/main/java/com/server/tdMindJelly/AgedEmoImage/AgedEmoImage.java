package com.server.tdMindJelly.AgedEmoImage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tdMindJelly.agedEmo.AgedEmo;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : AgedEmoImage
 * @description : AgedEmoImage(숙성된 감정에 들어가는 사진)의 Entity
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name="agedEmoImage")
public class AgedEmoImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agedEmoImageListId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agedEmoId", insertable = false, updatable=false)
    private AgedEmo agedEmo;

    @Column(nullable = false)
    private Long agedEmoId;

    @Column(nullable = false)
    private String imageName;

    @Builder
    public AgedEmoImage(Long agedEmoId, String imageName){
        this.agedEmoId = agedEmoId;
        this.imageName = imageName;
    }
}
