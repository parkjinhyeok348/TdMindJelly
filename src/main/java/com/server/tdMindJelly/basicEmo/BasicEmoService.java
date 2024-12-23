package com.server.tdMindJelly.basicEmo;

import com.server.tdMindJelly.basicEmo.DTO.BasicEmoResDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : BasicEmoService
 * @description : BasicEmo(기본 감정)의 Service
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BasicEmoService {

    private final BasicEmoRepository basicEmoRepository;

    public BasicEmoResDTO getBasicEmoById(Long emoId){
        BasicEmo basicEmo = basicEmoRepository.findByEmoId(emoId);
        return new BasicEmoResDTO(basicEmo);
    }

    public List<BasicEmoResDTO> getAllBasicEmoList(){
        List<BasicEmo> basicEmoList = basicEmoRepository.findAll();
        return basicEmoList.stream()
                .map(BasicEmoResDTO::new)
                .collect(Collectors.toList());
    }
}
