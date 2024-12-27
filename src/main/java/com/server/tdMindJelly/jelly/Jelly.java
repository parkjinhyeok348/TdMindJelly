package com.server.tdMindJelly.jelly;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.server.tdMindJelly.JellyCombination.JellyCombination;
import com.server.tdMindJelly.jellyImage.JellyImage;
import com.server.tdMindJelly.user.User;
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
 * @className : Jelly
 * @description : Jelly의 Entity
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@Entity
@NoArgsConstructor
@Table(name="jelly")
public class Jelly {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jellyId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", insertable = false, updatable=false)
    private User user;

    @Column(nullable = false)
    private Long userId;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jellyCombId", insertable = false, updatable=false)
    private JellyCombination jellyCombination;

    @Column(nullable = false)
    private Long jellyCombId;

    @Column
    private String jellyName; //젤리 이름

    @Column
    private String content; // 젤리에 남길 메모

    @Column
    private Boolean isAging; // 숙성 여부

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate agingPeriod; // 숙성 기간

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private LocalDate createDate; // 생성 날짜

    @JsonIgnore
    @OneToMany(mappedBy = "jelly", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<JellyImage> jellyImages =new ArrayList<>(); //젤리에 들어갈 사진 리스트

    @Builder
    public Jelly(Long jellyId, Long userId, Long jellyCombId, String jellyName, String content,
                 Boolean isAging, LocalDate agingPeriod, LocalDate createDate, List<JellyImage> jellyImages) {
        this.jellyId = jellyId;
        this.userId = userId;
        this.jellyCombId = jellyCombId;
        this.jellyName = jellyName;
        this.content = content;
        this.isAging = isAging;
        this.agingPeriod = agingPeriod;
        this.createDate = createDate;
        this.jellyImages=jellyImages;
    }

    public void updateJelly(String jellyName, String content,
                           List<JellyImage> jellyImages){
        this.jellyName = (jellyName != null && !jellyName.isBlank())? jellyName:this.jellyName;
        this.content = (content != null&& !content.isBlank())? content:this.content;
        if(jellyImages !=null){
            this.jellyImages.clear();
            this.jellyImages.addAll(jellyImages);
        }
    }
}
