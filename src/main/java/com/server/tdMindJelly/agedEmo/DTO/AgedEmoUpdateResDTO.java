package com.server.tdMindJelly.agedEmo.DTO;

import com.server.tdMindJelly.AgedEmoImage.AgedEmoImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : AgedEmoUpdateResDTO
 * @description : 숙성된 감정 상세 정보를 수정할 때 사용하는 AgedEmo Response DTO
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Getter
@NoArgsConstructor
public class AgedEmoUpdateResDTO {
    private String agedEmoName; // 숙성된 감정 이름
    private String content; // 숙성된 감정에 남길 메모
    private List<AgedEmoImage> agedEmoImages; // 숙성된 감정에 들어갈 사진 리스트

    @Builder
    public AgedEmoUpdateResDTO(String agedEmoName,String content, List<AgedEmoImage>agedEmoImages){
        this.agedEmoName = agedEmoName;
        this.content = content;
        this.agedEmoImages = agedEmoImages;
    }
}
