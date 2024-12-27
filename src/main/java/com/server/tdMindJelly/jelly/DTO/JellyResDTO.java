package com.server.tdMindJelly.jelly.DTO;

import com.server.tdMindJelly.jelly.Jelly;
import com.server.tdMindJelly.jellyImage.JellyImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : JellyResDTO
 * @description : Jelly 상세 정보를 가져올 때 사용하는 Jelly Response DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class JellyResDTO {
    private Long jellyId;
    private Long userId; // 유저 아이디
    private Long jellyCombId; // 젤리 조합 id
    private String jellyName; //젤리 이름
    private String content; // 젤리에 남길 메모
    private Boolean isAging; // 숙성 여부
    private LocalDate agingPeriod; // 숙성 기간
    private LocalDate createDate; // 생성 날짜
    private List<JellyImage> jellyImages; //젤리에 들어갈 사진 리스트

    @Builder
    public JellyResDTO(Jelly jelly) {
        this.jellyId = jelly.getJellyId();
        this.userId = jelly.getUserId();
        this.jellyCombId = jelly.getJellyCombId();
        this.jellyName = jelly.getJellyName();
        this.content = jelly.getContent();
        this.isAging = jelly.getIsAging();
        this.agingPeriod = jelly.getAgingPeriod();
        this.createDate = jelly.getCreateDate();
        this.jellyImages = jelly.getJellyImages();
    }
}
