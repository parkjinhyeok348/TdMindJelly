package com.server.tdMindJelly.AgedEmoImage;

import com.server.tdMindJelly.AgedEmoImage.DTO.AgedEmoImageResDTO;
import com.server.tdMindJelly.AgedEmoImage.DTO.AgedEmoImageSaveReqDTO;
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
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : AgedEmoImageController
 * @description : AgedEmoImage의 Controller
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Slf4j
@RestController
@RequestMapping("/agedEmoImage")
@RequiredArgsConstructor
public class AgedEmoImageController {

    private final AgedEmoImageService agedEmoImageService;

    // 숙성된 감정 사진 생성
    @PostMapping
    public ResponseEntity<AgedEmoImageResDTO> createAgedEmoImage(@RequestBody AgedEmoImageSaveReqDTO reqDTO) {
        AgedEmoImage createdImage = agedEmoImageService.createAgedEmoImage(reqDTO);
        return ResponseEntity.ok(new AgedEmoImageResDTO(createdImage));
    }

    // 숙성된 감정에 속한 사진 리스트 조회
    @GetMapping("/{agedEmoId}")
    public ResponseEntity<List<AgedEmoImageResDTO>> getAgedEmoImageList(@PathVariable Long agedEmoId) {
        List<AgedEmoImageResDTO> imageList = agedEmoImageService.getAgedEmoImageList(agedEmoId);
        return ResponseEntity.ok(imageList);
    }
}
