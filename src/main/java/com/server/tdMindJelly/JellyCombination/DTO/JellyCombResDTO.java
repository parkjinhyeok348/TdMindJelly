package com.server.tdMindJelly.JellyCombination.DTO;

import com.server.tdMindJelly.JellyCombination.JellyCombination;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : JellyCombResDTO
 * @description : JellyCombination의 상세 정보를 가져오느 Response DTO
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Getter
@NoArgsConstructor
public class JellyCombResDTO {
    private Long jellyCombId;
    private Long firstEmo;
    private Long secondEmo;
    private Boolean isAwaken;
    private String jellyIcon;

    @Builder
    public JellyCombResDTO(JellyCombination jellyCombination){
        this.jellyCombId = jellyCombination.getJellyCombId();
        this.firstEmo = jellyCombination.getFirstEmo();
        this.secondEmo = jellyCombination.getSecondEmo();
        this.isAwaken = jellyCombination.getIsAwaken();
        this.jellyIcon = jellyCombination.getJellyIcon();
    }
}
