package com.server.tdMindJelly.jellyImage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : JellyImageRepository
 * @description :JellyImage의 Repository
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
public interface JellyImageRepository extends JpaRepository<JellyImage, Long> {
    List<JellyImage> findByJellyId(@Param("jellyId") Long jellyId);
}
