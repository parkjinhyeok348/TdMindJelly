package com.server.tdMindJelly.agedEmo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tdMindJelly.AgedEmoImage.AgedEmoImage;
import com.server.tdMindJelly.JellyCombination.JellyCombination;
import com.server.tdMindJelly.user.Users;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : AgedEmo
 * @description : AgedEmo(숙성된 젤리)의 Entity
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name="agedEmo")
public class AgedEmo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agedEmoId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable=false)
    private Users users;

    @Column(nullable = false)
    private Long userId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jellyCombId", insertable = false, updatable=false)
    private JellyCombination jellyCombination;

    @Column(nullable = false)
    private Long jellyCombId;

    @Column
    private String agedEmoName;

    @Column
    private String content; // 젤리에 남길 메모

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate createDate; // 생성 날짜

    @JsonIgnore
    @OneToMany(mappedBy = "agedEmo", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AgedEmoImage> agedEmoImages =new ArrayList<>(); //젤리에 들어갈 사진 리스트

    @Builder
    public AgedEmo(Long userId, Long jellyCombId,String agedEmoName,
                   String content, LocalDate createDate,List<AgedEmoImage> agedEmoImages) {
        this.userId = userId;
        this.jellyCombId = jellyCombId;
        this.agedEmoName = agedEmoName;
        this.content = content;
        this.createDate = createDate;
        this.agedEmoImages = agedEmoImages;
    }
    public void updateAgedEmo(String agedEmoName, String content,
                            List<AgedEmoImage> agedEmoImages){
        this.agedEmoName = (agedEmoName != null && !agedEmoName.isBlank())? agedEmoName:this.agedEmoName;
        this.content = (content != null&& !content.isBlank())? content:this.content;
        if(agedEmoImages !=null){
            this.agedEmoImages.clear();
            this.agedEmoImages.addAll(agedEmoImages);
        }
    }
}
