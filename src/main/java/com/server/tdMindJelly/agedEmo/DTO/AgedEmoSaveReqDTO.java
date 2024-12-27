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
 * @className : AgedEmoReqDTO
 * @description : AgedEmo를 생성할 때 사용하는 AgedEmo Request DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class AgedEmoSaveReqDTO {
    private Long userId; // 유저 아이디
    private Long jellyCombId; // 젤리 조합 id
    private String AgedEmoName; // 숙성된 감정 이름
    private String content; // 숙성된 감정에 남길 메모
    private LocalDate createDate; // 생성 날짜
    private List<AgedEmoImage> agedEmoImages; // 숙성된 감정에 들어갈 사진 리스트

    @Builder
    public AgedEmoSaveReqDTO(Long userId, Long jellyCombId,String AgedEmoName, String content,
                             LocalDate createDate, List<AgedEmoImage> agedEmoImages) {
        this.userId = userId;
        this.jellyCombId = jellyCombId;
        this.AgedEmoName = AgedEmoName;
        this.content = content;
        this.createDate = createDate;
        this.agedEmoImages = agedEmoImages;
    }

    public AgedEmo toEntity() {
        return AgedEmo.builder()
                .userId(this.userId)
                .jellyCombId(this.jellyCombId)
                .agedEmoName(this.AgedEmoName)
                .content(this.content)
                .createDate(this.createDate != null ? this.createDate : LocalDate.now()) // createDate가 null인 경우 현재 날짜로 설정
                .agedEmoImages(this.agedEmoImages)
                .build();
    }

}
