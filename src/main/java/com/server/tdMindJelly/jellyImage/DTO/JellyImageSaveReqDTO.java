package com.server.tdMindJelly.jellyImage.DTO;

import com.server.tdMindJelly.jellyImage.JellyImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : KTDS
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     KTDS      주석최초생성
 * @className : JellyImageSaveReqDTO
 * @description : 젤리에 들어갈 사진 생성하는 JellyImage Request DTO
 * @modification : 2024-12-26 (KTDS)
 * @date : 2024-12-26
 */
@Getter
@NoArgsConstructor
public class JellyImageSaveReqDTO {
    private Long jellyId;
    private String imageName;

    @Builder
    public JellyImageSaveReqDTO(Long jellyId,String imageName){
        this.jellyId = jellyId;
        this.imageName = imageName;
    }

    public JellyImage toEntity(){
        return JellyImage.builder()
                .jellyId(jellyId)
                .imageName(imageName)
                .build();
    }
}
