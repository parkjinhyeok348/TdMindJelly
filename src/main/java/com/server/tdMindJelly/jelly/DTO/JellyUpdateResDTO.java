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
 * 2024-12-24   Jinhyeok    주석최초생성
 * @className : JellyUpdateResDTO
 * @description :Jelly 상세 정보 수정할 때 사용하는 Jelly Response DTO
 * @modification : 2024-12-24 (Jinhyeok)
 * @date : 2024-12-24
 */
@Getter
@NoArgsConstructor
public class JellyUpdateResDTO {
    private String jellyName; //젤리 이름
    private String content; // 젤리에 남길 메모
    private List<JellyImage> jellyImages; //젤리에 들어갈 사진 리스트

    @Builder
    public JellyUpdateResDTO(String jellyName, String content, List<JellyImage> jellyImages){
        this.jellyName = jellyName;
        this.content = content;
        this.jellyImages = jellyImages;
    }
}
