package com.server.tdMindJelly.agedEmo;

import com.server.tdMindJelly.agedEmo.DTO.*;
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
 * @className : AgedEmoController
 * @description : AgedEmo의 Controller
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@RestController
@RequestMapping("/agedEmo")
@RequiredArgsConstructor
public class AgedEmoController {
    private final AgedEmoService agedEmoService;

    @PostMapping
    public ResponseEntity<AgedEmo> createAgedEmo(@RequestBody AgedEmoSaveReqDTO agedEmoSaveReqDTO) {
        AgedEmo createdAgedEmo = agedEmoService.createAgedEmo(agedEmoSaveReqDTO);
        return ResponseEntity.ok(createdAgedEmo);
    }

    // 숙성된 감정 업데이트를 위한 상세 정보 출력
    @GetMapping("/{agedEmoId}/update-info")
    public ResponseEntity<AgedEmoUpdateResDTO> getAgedEmoInfo(@PathVariable Long agedEmoId) {
        AgedEmoUpdateResDTO response = agedEmoService.getAgedEmoInfo(agedEmoId);
        return ResponseEntity.ok(response);
    }

    // 숙성된 감정 업데이트
    @PutMapping("/{agedEmoId}")
    public ResponseEntity<AgedEmo> updateAgedEmo(
            @PathVariable Long agedEmoId,
            @RequestBody AgedEmoUpdateReqDTO reqDTO) {
        AgedEmo updatedAgedEmo = agedEmoService.updateAgedEmo(agedEmoId, reqDTO);
        return ResponseEntity.ok(updatedAgedEmo);
    }

    // 숙성된 감정 상세 정보 출력
    @GetMapping("/{agedEmoId}")
    public ResponseEntity<AgedEmoResDTO> getAgedEmoById(@PathVariable Long agedEmoId) {
        AgedEmoResDTO response = agedEmoService.getAgedEmoById(agedEmoId);
        return ResponseEntity.ok(response);
    }

    // 젤리 뮤지엄에 개인 숙성된 감정 목록 출력
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AgedEmoMuseumResDTO>> getAgedEmoList(@PathVariable Long userId) {
        List<AgedEmoMuseumResDTO> response = agedEmoService.getAgedEmoList(userId);
        return ResponseEntity.ok(response);
    }

}
