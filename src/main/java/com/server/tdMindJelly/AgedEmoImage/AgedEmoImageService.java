package com.server.tdMindJelly.AgedEmoImage;

import com.server.tdMindJelly.AgedEmoImage.DTO.AgedEmoImageResDTO;
import com.server.tdMindJelly.AgedEmoImage.DTO.AgedEmoImageSaveReqDTO;
import com.server.tdMindJelly.jellyImage.DTO.JellyImageResDTO;
import com.server.tdMindJelly.jellyImage.DTO.JellyImageSaveReqDTO;
import com.server.tdMindJelly.jellyImage.JellyImage;
import com.server.tdMindJelly.jellyImage.JellyImageRepository;
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
 * 2024-12-26     Jinhyeok      주석최초생성
 * @className : AgedEmoImageService
 * @description : AgedEmoImage의 Service
 * @modification : 2024-12-26 (Jinhyeok)
 * @date : 2024-12-26
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AgedEmoImageService {
    private final AgedEmoImageRepository agedEmoImageRepository;

    //숙성된 감정 사진 생성
    public AgedEmoImage createAgedEmoImage(AgedEmoImageSaveReqDTO reqDTO){
        AgedEmoImage agedEmoImage = reqDTO.toEntity();
        return agedEmoImageRepository.save(agedEmoImage);
    }

    //숙성된 감정에 속한 사진 리스트를 출력
    public List<AgedEmoImageResDTO> getAgedEmoImageList(Long agedEmoId){
        return Optional.ofNullable(agedEmoImageRepository.findByAgedEmoId(agedEmoId))
                .orElse(Collections.emptyList())
                .stream()
                .map(AgedEmoImageResDTO::new) // 각 숙성된 감정 사진들을 ResDTO로 변환
                .collect(Collectors.toList());
    }
}
