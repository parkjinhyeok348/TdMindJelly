package com.server.tdMindJelly.jellyImage;

import com.server.tdMindJelly.jelly.DTO.JellyDrawerResDTO;
import com.server.tdMindJelly.jelly.DTO.JellyResDTO;
import com.server.tdMindJelly.jellyImage.DTO.JellyImageResDTO;
import com.server.tdMindJelly.jellyImage.DTO.JellyImageSaveReqDTO;
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
 * @className : JellyImageService
 * @description : JellyImage의 Service
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class JellyImageService {

    private final JellyImageRepository jellyImageRepository;

    //젤리 이미지 생성
    public JellyImage createJellyImage(JellyImageSaveReqDTO reqDTO){
        JellyImage jellyImage = reqDTO.toEntity();
        return jellyImageRepository.save(jellyImage);
    }

    //젤리에 속한 젤리이미지를 출력
    public List<JellyImageResDTO> getJellyImageList(Long jellyId){
        return Optional.ofNullable(jellyImageRepository.findByJellyId(jellyId))
                .orElse(Collections.emptyList())
                .stream()
                .map(JellyImageResDTO::new) // 각 젤리이미지를 ResDTO로 변환
                .collect(Collectors.toList());
    }
    
    //젤리 이미지 삭제
    public void deleteJellyImage(){jellyImageRepository.deleteAll();}

}
