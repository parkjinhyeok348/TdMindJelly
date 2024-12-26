package com.server.tdMindJelly.jelly;

import com.server.tdMindJelly.jelly.DTO.*;
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
 * @className : JellyService
 * @description : Jelly의 Service
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JellyService {

    private final JellyRepository jellyRepository;

    //젤리 생성
    public Jelly createJelly(JellySaveReqDTO jellySaveReqDTO){
        Jelly jelly = jellySaveReqDTO.toEntity();
        return jellyRepository.save(jelly);
    }

    //젤리 정보 업데이트를 위한 프로필 정보 출력
    public JellyUpdateResDTO getJellyInfo(Long jellyId){
        Jelly jelly = jellyRepository.findById(jellyId).orElseThrow(() -> new EntityNotFoundException("Jelly not found"));
        return new JellyUpdateResDTO(jelly.getJellyName(), jelly.getContent(), jelly.getJellyImages());
    }

    //젤리 정보 업데이트
    public Jelly updateJelly(Long jellyId,JellyUpdateReqDTO reqDTO){
        Jelly jelly = jellyRepository.findById(jellyId).orElseThrow(() -> new EntityNotFoundException("Jelly not found"));
        jelly.updateJelly(reqDTO.getJellyName(), reqDTO.getContent(), reqDTO.getJellyImages());
        return jelly;
    }

    // 젤리 상세 정보 출력
    public JellyResDTO getJellyById(Long jellyId){
        Jelly entity = jellyRepository.findById(jellyId).orElseThrow(() -> new EntityNotFoundException("Jelly not found"));
        return new JellyResDTO(entity);
    }

    // 젤리 서랍에 개인 젤리 목록 출력하기
    public List<JellyDrawerResDTO> getJellyList(Long userId) {
        return Optional.ofNullable(jellyRepository.findByUserId(userId))
                .orElse(Collections.emptyList())
                .stream()
                .map(JellyDrawerResDTO::new) // 각 젤리를 ResDTO로 변환
                .collect(Collectors.toList());
    }
}
