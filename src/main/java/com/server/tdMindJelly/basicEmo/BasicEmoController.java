package com.server.tdMindJelly.basicEmo;

import com.server.tdMindJelly.basicEmo.DTO.BasicEmoResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class BasicEmoController {

    private final BasicEmoService basicEmoService;

    // 감정id로 감정 1개 조회
    @GetMapping(value = "basicEmo/{emoId}")
    public BasicEmoResDTO getBasicEmo(@PathVariable("emoId")Long emoId){
        return basicEmoService.getBasicEmoById(emoId);
    }

    // 감정 리스트 전부 조회
    @GetMapping(value = "basicEmo/all")
    public List<BasicEmoResDTO> getAllBasicEmoList(){
        return basicEmoService.getAllBasicEmoList();
    }

}
