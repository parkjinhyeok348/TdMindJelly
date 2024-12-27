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
 * @className : JellySaveReqDTO
 * @description : Jelly 생성 시 사용하는 Jelly Request DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class JellySaveReqDTO {
    private String jellyName; //젤리 이름
    private Long jellyCombId; // 젤리 조합 id
    private String content; // 젤리에 남길 메모
    private LocalDate agingPeriod; // 숙성 기간
    private LocalDate createDate; // 생성 날짜
    private Long userId; // 사용자 id
    private List<JellyImage> jellyImages; // 젤리에 들어갈 사진 리스트

    @Builder
    public JellySaveReqDTO(String jellyName, Long jellyCombId, String content, LocalDate agingPeriod,
                           LocalDate createDate,Long userId,List<JellyImage> jellyImages) {
        this.jellyName = jellyName;
        this.jellyCombId = jellyCombId;
        this.content = content;
        this.agingPeriod = agingPeriod;
        this.createDate = createDate;
        this.userId = userId;
        this.jellyImages = jellyImages;
    }

    public Jelly toEntity() {
        return Jelly.builder()
                .jellyName(jellyName)
                .jellyCombId(jellyCombId)
                .content(content)
                .agingPeriod(agingPeriod)
                .createDate(this.createDate != null ? this.createDate : LocalDate.now()) // createDate가 null인 경우 현재 날짜로 설정
                .isAging(false)
                .userId(userId)
                .jellyImages(jellyImages)
                .build();
    }

}
