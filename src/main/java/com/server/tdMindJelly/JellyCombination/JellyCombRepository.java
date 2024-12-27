package com.server.tdMindJelly.JellyCombination;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : JellyCombRepository
 * @description : JellyCombination(젤리 조합식)의 Repository
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
public interface JellyCombRepository extends JpaRepository<JellyCombination,Long> {
    JellyCombination findByFirstEmoAndSecondEmoAndIsAwaken(Long firstEmo, Long secondEmo, Boolean isAwaken);
}
