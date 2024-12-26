package com.server.tdMindJelly.jellyImage.DTO;

import com.server.tdMindJelly.jellyImage.JellyImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : JellyImageResDTO
 * @description : JellyImage의 정보를 가져올 때 사용하는 JellyImage Response DTO
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Getter
@NoArgsConstructor
public class JellyImageResDTO {
    private Long jellyImageId;
    private Long jellyId;
    private String imageName;

    @Builder
    public JellyImageResDTO(JellyImage jellyImage){
        this.jellyImageId = jellyImage.getJellyImageListId();
        this.jellyId = jellyImage.getJellyId();
        this.imageName = jellyImage.getImageName();
    }
}
