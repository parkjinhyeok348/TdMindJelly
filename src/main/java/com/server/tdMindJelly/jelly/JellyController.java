package com.server.tdMindJelly.jelly;

import com.server.tdMindJelly.jelly.DTO.*;
import lombok.NoArgsConstructor;
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
 * @className : JellyController
 * @description : Jelly의 Controller
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@RestController
@RequestMapping("/jelly")
@RequiredArgsConstructor
public class JellyController {

    private final JellyService jellyService;

    // 젤리 생성
    @PostMapping
    public ResponseEntity<Jelly> createJelly(@RequestBody JellySaveReqDTO jellySaveReqDTO) {
        Jelly createdJelly = jellyService.createJelly(jellySaveReqDTO);
        return ResponseEntity.ok(createdJelly);
    }

    // 젤리 정보 조회 (젤리 정보 수정용)
    @GetMapping("/{jellyId}/info")
    public ResponseEntity<JellyUpdateResDTO> getJellyInfo(@PathVariable Long jellyId) {
        JellyUpdateResDTO jellyInfo = jellyService.getJellyInfo(jellyId);
        return ResponseEntity.ok(jellyInfo);
    }

    // 젤리 정보 업데이트
    @PutMapping("/{jellyId}")
    public ResponseEntity<Jelly> updateJelly(@PathVariable Long jellyId, @RequestBody JellyUpdateReqDTO reqDTO) {
        Jelly updatedJelly = jellyService.updateJelly(jellyId, reqDTO);
        return ResponseEntity.ok(updatedJelly);
    }

    // 젤리 상세 정보 조회
    @GetMapping("/{jellyId}")
    public ResponseEntity<JellyResDTO> getJellyById(@PathVariable Long jellyId) {
        JellyResDTO jellyDetails = jellyService.getJellyById(jellyId);
        return ResponseEntity.ok(jellyDetails);
    }

    // 특정 유저의 젤리 목록 조회
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<JellyDrawerResDTO>> getJellyList(@PathVariable Long userId) {
        List<JellyDrawerResDTO> jellyList = jellyService.getJellyList(userId);
        return ResponseEntity.ok(jellyList);
    }
}