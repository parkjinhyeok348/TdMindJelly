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
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : AgedEmoUpdateReqDTO
 * @description : 숙성된 감정 내용을 수정하기 위해 사용될 AgedEmo Request DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class AgedEmoUpdateReqDTO {

    private String AgedEmoName;// 숙성된 젤리 이름
    private String content; // 숙성된 젤리에 남길 메모
    private List<AgedEmoImage> agedEmoImages; // 숙성된 젤리에 들어갈 사진 리스트

    @Builder
    public AgedEmoUpdateReqDTO(String agedEmoName,String content, List<AgedEmoImage> agedEmoImages){
        this.AgedEmoName = agedEmoName;
        this.content =content;
        this.agedEmoImages = agedEmoImages;
    }

}
