package com.server.tdMindJelly.agedEmo;

import com.server.tdMindJelly.agedEmo.DTO.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : AgedEmoService
 * @description : AgedEmo의 Service
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AgedEmoService {

    private AgedEmoRepository agedEmoRepository;

    //숙성된 감정 생성
    public AgedEmo createAgedEmo(AgedEmoSaveReqDTO agedEmoSaveReqDTO){
        AgedEmo agedEmo = agedEmoSaveReqDTO.toEntity();
        return agedEmoRepository.save(agedEmo);
    }

    // 숙성된 감정 업데이트를 위한 상세 정보 출력
    public AgedEmoUpdateResDTO getAgedEmoInfo(Long agedEmoId){
        AgedEmo agedEmo = agedEmoRepository.findById(agedEmoId).orElseThrow(() -> new EntityNotFoundException("AgedEmo not found"));
        return new AgedEmoUpdateResDTO(agedEmo.getAgedEmoName(),agedEmo.getContent(),agedEmo.getAgedEmoImages());
    }

    // 숙성된 감정 업데이트
    public AgedEmo updateAgedEmo(Long agedEmoId, AgedEmoUpdateReqDTO reqDTO){
        AgedEmo agedEmo = agedEmoRepository.findById(agedEmoId).orElseThrow(() -> new EntityNotFoundException("AgedEmo not found"));
        agedEmo.updateAgedEmo(reqDTO.getAgedEmoName(),reqDTO.getContent(),reqDTO.getAgedEmoImages());
        return agedEmo;
    }

    // 숙성된 감정 상세 정보 출력
    public AgedEmoResDTO getAgedEmoById(Long agedEmoId){
        AgedEmo entity = agedEmoRepository.findById(agedEmoId).orElseThrow(() -> new EntityNotFoundException("AgedEmo not found"));
        return new AgedEmoResDTO(entity);
    }

    // 젤리 뮤지엄에 개인 숙성된 감정 목록 출력
    public List<AgedEmoMuseumResDTO> getAgedEmoList(Long userId){
        return Optional.ofNullable(agedEmoRepository.findByUserId(userId))
                .orElse(Collections.emptyList())
                .stream()
                .map(AgedEmoMuseumResDTO::new) // 각 숙성된 젤리를 ResDTO로 변환
                .collect(Collectors.toList());
    }


}
