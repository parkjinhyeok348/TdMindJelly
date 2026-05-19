package com.server.tdMindJelly.JellyCombination;

import com.server.tdMindJelly.JellyCombination.DTO.JellyCombResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : JellyCombController
 * @description : JellyCombination(젤리 조합식)의 Controller
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Slf4j
@RestController
@RequestMapping("/jellyComb")
@RequiredArgsConstructor
public class JellyCombController {

    private final JellyCombService jellyCombService;

    // 젤리 조합 정보 조회 (ID로 조회)
    @GetMapping("/{jellyCombId}")
    public ResponseEntity<JellyCombResDTO> getJellyCombById(@PathVariable("jellyCombId") Long jellyCombId) {
        JellyCombResDTO response = jellyCombService.getJellyCombById(jellyCombId);
        return ResponseEntity.ok(response);
    }

    // 감정 조합으로 젤리 아이콘 반환
    @GetMapping(value = "/jelly-icon/{firstEmo}/{secondEmo}/{isAwaken}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getJellyIcon(
            @PathVariable("firstEmo") Long firstEmo,
            @PathVariable("secondEmo") Long secondEmo,
            @PathVariable("isAwaken") Boolean isAwaken) {
        String jellyIcon = jellyCombService.getJellyIcon(firstEmo, secondEmo, isAwaken);
        // JSON 파싱 오류(malformed JSON) 방지를 위해 쌍따옴표를 포함한 JSON 문자열 반환
        return ResponseEntity.ok("\"" + jellyIcon + "\"");
    }
}
