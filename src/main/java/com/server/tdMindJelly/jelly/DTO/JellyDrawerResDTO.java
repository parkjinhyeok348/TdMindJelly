package com.server.tdMindJelly.jelly.DTO;

import com.server.tdMindJelly.jelly.Jelly;
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
 * @className : JellyDrawerResDTO
 * @description : 젤리 서랍에서 보일 개인 젤리 리스트을 위한 Jelly Response DTO
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Getter
@NoArgsConstructor
public class JellyDrawerResDTO {
    private Long jellyId;
    private Long jellyCombId; // 젤리 조합 id
    private Boolean isAging; // 숙성 여부
    private LocalDate createDate; // 생성 날짜

    @Builder
    public JellyDrawerResDTO(Jelly jelly){
        this.jellyId = jelly.getJellyId();
        this.jellyCombId = jelly.getJellyCombId();
        this.isAging = jelly.getIsAging();
        this.createDate = jelly.getCreateDate();
    }
}
