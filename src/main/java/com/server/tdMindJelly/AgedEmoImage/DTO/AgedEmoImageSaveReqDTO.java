package com.server.tdMindJelly.AgedEmoImage.DTO;

import com.server.tdMindJelly.AgedEmoImage.AgedEmoImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : AgedEmoImageSaveReqDTO
 * @description :
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Getter
@NoArgsConstructor
public class AgedEmoImageSaveReqDTO {
    private Long agedEmoId;
    private String imageName;

    @Builder
    public AgedEmoImageSaveReqDTO(Long agedEmoId,String imageName){
        this.agedEmoId = agedEmoId;
        this.imageName = imageName;
    }

    public AgedEmoImage toEntity(){
        return AgedEmoImage.builder()
                .agedEmoId(agedEmoId)
                .imageName(imageName)
                .build();
    }
}
