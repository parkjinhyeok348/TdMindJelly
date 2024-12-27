package com.server.tdMindJelly.JellyCombination;

import com.server.tdMindJelly.JellyCombination.DTO.JellyCombResDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : JellyCombService
 * @description : JellyCombination(젤리 조합식)의 Service
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JellyCombService {

    private JellyCombRepository jellyCombRepository;

    //젤리 조합 정보 조회
    public JellyCombResDTO getJellyCombById(Long jellyCombId){
        JellyCombination jellyCombination = jellyCombRepository.findById(jellyCombId).orElseThrow(() -> new EntityNotFoundException("JellyCombination not found"));
        return new JellyCombResDTO(jellyCombination);
    }

    // 감정 조합으로 젤리 아이콘 반환
    public String getJellyIcon(Long firstEmo, Long secondEmo, Boolean isAwaken){
        JellyCombination jellyCombination = jellyCombRepository.findByFirstEmoAndSecondEmoAndIsAwaken(firstEmo,secondEmo,isAwaken);
        return jellyCombination.getJellyIcon();
    }

}
