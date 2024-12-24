package com.server.tdMindJelly.user;

import com.server.tdMindJelly.user.DTO.UserResDTO;
import com.server.tdMindJelly.user.DTO.UserSaveReqDTO;
import com.server.tdMindJelly.user.DTO.UserUpdateReqDTO;
import com.server.tdMindJelly.user.DTO.UserUpdateResDTO;
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
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : UserService
 * @description : User(사용자)의 Service
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    //새로운 유저 생성
    public User createUser(UserSaveReqDTO userSaveReqDTO){
        User user = userSaveReqDTO.toEntity();
        return userRepository.save(user);
    }
    
    //유저 정보 업데이트를 위한 프로필 정보 출력
    public UserUpdateResDTO getUserProfile(Long userId){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new UserUpdateResDTO(user.getPassword(), user.getNickName(), user.getProfileImage(),user.getIsMarketing());
    }

    // 유저 정보 업데이트
    public User updateUser(Long userId, UserUpdateReqDTO reqDTO){
        User user = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.updateUser(reqDTO.getPassword(),reqDTO.getNickName(),reqDTO.getProfileImage(),reqDTO.getIsMarketing());
        return user;
    }

    // 사용자 아이디로 유저 상세정보 출력
    public UserResDTO getUserById(Long userId){
        User entity = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new UserResDTO(entity);
    }

    // 이메일 중복 여부 확인
    public Boolean checkDuplicateEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user == null;
    }

    // 전화번호 중복 여부 확인
    public Boolean checkDuplicateMobilePhoneNumber(String mobilePhoneNumber) {
        User user = userRepository.findByMobilePhoneNumber(mobilePhoneNumber);
        return user == null;
    }

    //닉네임 중복 여부 확인
    public Boolean checkDuplicateNickName(String nickName) {
        User user = userRepository.findByNickName(nickName);
        return user == null;
    }

}
