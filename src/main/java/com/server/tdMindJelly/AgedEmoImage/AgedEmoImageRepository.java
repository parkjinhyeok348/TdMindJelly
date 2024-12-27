package com.server.tdMindJelly.AgedEmoImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : AgedEmoImageRepository
 * @description : AgedEmoImage의 Repository
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
public interface AgedEmoImageRepository extends JpaRepository<AgedEmoImage,Long> {
    List<AgedEmoImage> findByAgedEmoId(@Param("agedEmoId") Long agedEmoId);
}
