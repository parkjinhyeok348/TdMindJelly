package com.server.tdMindJelly.jellyImage;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tdMindJelly.jelly.Jelly;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : JellyImage
 * @description : JellyImage의 Entity
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name="jellyImage")
public class JellyImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jellyImageListId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jellyId", insertable = false, updatable=false)
    private Jelly jelly;

    @Column(nullable = false)
    private Long jellyId;

    @Column(nullable = false)
    private String imageName;

    @Builder
    public JellyImage(Long jellyId, String imageName){
        this.jellyId = jellyId;
        this.imageName = imageName;
    }
}
