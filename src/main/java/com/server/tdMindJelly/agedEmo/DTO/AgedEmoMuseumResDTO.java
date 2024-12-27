package com.server.tdMindJelly.agedEmo.DTO;

import com.server.tdMindJelly.agedEmo.AgedEmo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : AgedEmoMuseumResDTO
 * @description : 젤리 뮤지엄에서 보일 개인 숙성된 감정 리스트를 위한 AgedEmo Response DTO
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Getter
@NoArgsConstructor
public class AgedEmoMuseumResDTO {
    private Long AgedEmoId;
    private Long jellyCombId; // 젤리 조합 id
    private LocalDate createDate; // 생성 날짜

    @Builder
    public AgedEmoMuseumResDTO(AgedEmo agedEmo){
        this.AgedEmoId = agedEmo.getAgedEmoId();
        this.jellyCombId = agedEmo.getJellyCombId();
        this.createDate = agedEmo.getCreateDate();
    }

}
