package com.server.tdMindJelly.agedEmo.DTO;

import com.server.tdMindJelly.AgedEmoImage.AgedEmoImage;
import com.server.tdMindJelly.agedEmo.AgedEmo;
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
 * @className : AgedEmoResDTO
 * @description : AgedEmo 상세 정보를 가져올 때 사용하는 AgedEmo Response DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class AgedEmoResDTO {
    private Long AgedEmoId;
    private Long userId; // 유저 id
    private Long jellyCombId; // 젤리 조합 id
    private String AgedEmoName; // 숙성된 감정 이름
    private String content; // 숙성된 감정에 남길 메모
    private LocalDate createDate; // 생성 날짜
    private List<AgedEmoImage> agedEmoImages; // 숙성된 감정에 들어갈 사진 리스트

    @Builder
    public AgedEmoResDTO(AgedEmo agedEmo) {
        this.AgedEmoId = agedEmo.getAgedEmoId();
        this.userId = agedEmo.getUserId();
        this.jellyCombId = agedEmo.getJellyCombId();
        this.AgedEmoName = agedEmo.getAgedEmoName();
        this.content = agedEmo.getContent();
        this.createDate = agedEmo.getCreateDate();
        this.agedEmoImages = agedEmo.getAgedEmoImages();
    }

}
