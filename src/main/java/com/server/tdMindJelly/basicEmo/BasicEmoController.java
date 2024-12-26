package com.server.tdMindJelly.basicEmo;

import com.server.tdMindJelly.basicEmo.DTO.BasicEmoResDTO;
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
 * @className : BasicEmoController
 * @description : BasicEmo(기본 감정)의 Controller
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */

@Slf4j
@RestController
@RequestMapping(value = "/basicEmo")
@RequiredArgsConstructor
public class BasicEmoController {

    private final BasicEmoService basicEmoService;

    @GetMapping("/{emoId}")
    public ResponseEntity<BasicEmoResDTO> getBasicEmoById(@PathVariable Long emoId) {
        BasicEmoResDTO basicEmoResDTO = basicEmoService.getBasicEmoById(emoId);
        return ResponseEntity.ok(basicEmoResDTO);
    }

    // 모든 기본 감정 리스트 조회
    @GetMapping
    public ResponseEntity<List<BasicEmoResDTO>> getAllBasicEmoList() {
        List<BasicEmoResDTO> basicEmoList = basicEmoService.getAllBasicEmoList();
        return ResponseEntity.ok(basicEmoList);
    }

}
