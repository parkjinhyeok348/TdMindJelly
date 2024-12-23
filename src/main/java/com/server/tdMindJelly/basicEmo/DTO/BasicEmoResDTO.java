package com.server.tdMindJelly.basicEmo.DTO;

import com.server.tdMindJelly.basicEmo.BasicEmo;
import lombok.*;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : BasicEmoResDTO
 * @description : 감정의 정보를 가져오기 위해 사용되는 BasicEmo Response DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class BasicEmoResDTO {
    private Long emoId;
    private String emoName;
    private String emoIcon;

    @Builder
    public BasicEmoResDTO(BasicEmo basicEmo){
        this.emoId = basicEmo.getEmoId();
        this.emoName = basicEmo.getEmoName();
        this.emoIcon=basicEmo.getEmoIcon();
    }
}
