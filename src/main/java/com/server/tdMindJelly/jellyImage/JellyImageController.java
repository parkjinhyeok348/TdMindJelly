package com.server.tdMindJelly.jellyImage;

import com.server.tdMindJelly.jellyImage.DTO.JellyImageResDTO;
import com.server.tdMindJelly.jellyImage.DTO.JellyImageSaveReqDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : JellyImageController
 * @description : JellyImage의 Controller
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@RestController
@RequestMapping("/jellyImage")
@RequiredArgsConstructor
public class JellyImageController {

    private final JellyImageService jellyImageService;

    // 젤리 이미지 생성
    @PostMapping
    public ResponseEntity<JellyImageResDTO> createJellyImage(@RequestBody JellyImageSaveReqDTO reqDTO) {
        JellyImage createdJellyImage = jellyImageService.createJellyImage(reqDTO);
        return ResponseEntity.ok(new JellyImageResDTO(createdJellyImage));
    }

    // 특정 젤리에 속한 젤리 이미지 리스트 조회
    @GetMapping("/{jellyId}")
    public ResponseEntity<List<JellyImageResDTO>> getJellyImageList(@PathVariable Long jellyId) {
        List<JellyImageResDTO> jellyImageList = jellyImageService.getJellyImageList(jellyId);
        return ResponseEntity.ok(jellyImageList);
    }

    // 젤리 이미지 전체 삭제
    @DeleteMapping
    public ResponseEntity<Void> deleteAllJellyImages() {
        jellyImageService.deleteJellyImage();
        return ResponseEntity.noContent().build();
    }
}

