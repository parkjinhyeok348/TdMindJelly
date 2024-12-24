package com.server.tdMindJelly.jelly.DTO;

import com.server.tdMindJelly.jellyImage.JellyImage;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-24     Jinhyeok      주석최초생성
 * @className : JellyUpdateReqDTO
 * @description : 젤리 내용을 수정하기 위해 사용될 Jelly Request DTO
 * @modification : 2024-12-24 (Jinhyeok)
 * @date : 2024-12-24
 */
@Getter
@NoArgsConstructor
public class JellyUpdateReqDTO {
    private String jellyName; //젤리 이름
    private String content; // 젤리에 남길 메모
    private List<JellyImage> jellyImages; //젤리에 들어갈 사진 리스트

    @Builder
    public JellyUpdateReqDTO(String jellyName, String content, List<JellyImage> jellyImages){
        this.jellyName = jellyName;
        this.content = content;
        this.jellyImages = jellyImages;
    }
}
