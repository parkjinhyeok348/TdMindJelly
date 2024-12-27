package com.server.tdMindJelly.agedEmo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : AgedEmoRepository
 * @description : AgedEmo의 Repository
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
public interface AgedEmoRepository extends JpaRepository<AgedEmo,Long> {
    List<AgedEmo> findByUserId(@Param("userId")Long userId);
}
