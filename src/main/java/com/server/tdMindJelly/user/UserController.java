package com.server.tdMindJelly.user;

import com.server.tdMindJelly.user.DTO.UserResDTO;
import com.server.tdMindJelly.user.DTO.UserSaveReqDTO;
import com.server.tdMindJelly.user.DTO.UserUpdateReqDTO;
import com.server.tdMindJelly.user.DTO.UserUpdateResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author : Jinhyeok
 * @version : 1.0
 * ====개정이력(Modification Information)====
 * 수정일        수정자        수정내용    -----------------------------------------
 * 2024-12-23     Jinhyeok      주석최초생성
 * @className : UserController
 * @description : User(사용자)의 Controller
 * @modification : 2024-12-23 (Jinhyeok)
 * @date : 2024-12-23
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 새로운 유저 생성
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody UserSaveReqDTO userSaveReqDTO) {
        User createdUser = userService.createUser(userSaveReqDTO);
        return ResponseEntity.ok(createdUser);
    }

    // 유저 정보 업데이트를 위한 프로필 정보 출력
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserUpdateResDTO> getUserProfile(@PathVariable Long userId) {
        UserUpdateResDTO userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    // 유저 정보 업데이트
    @PutMapping("/{userId}")
    public ResponseEntity<User> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateReqDTO reqDTO) {
        User updatedUser = userService.updateUser(userId, reqDTO);
        return ResponseEntity.ok(updatedUser);
    }

    // 사용자 아이디로 유저 상세 정보 출력
    @GetMapping("/{userId}")
    public ResponseEntity<UserResDTO> getUserById(@PathVariable Long userId) {
        UserResDTO userResDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userResDTO);
    }

    // 이메일 중복 여부 확인
    @GetMapping("/check-email")
    public ResponseEntity<Boolean> checkDuplicateEmail(@RequestParam String email) {
        Boolean isEmailAvailable = userService.checkDuplicateEmail(email);
        return ResponseEntity.ok(isEmailAvailable);
    }

    // 전화번호 중복 여부 확인
    @GetMapping("/check-phone")
    public ResponseEntity<Boolean> checkDuplicateMobilePhoneNumber(@RequestParam String mobilePhoneNumber) {
        Boolean isPhoneNumberAvailable = userService.checkDuplicateMobilePhoneNumber(mobilePhoneNumber);
        return ResponseEntity.ok(isPhoneNumberAvailable);
    }

    // 닉네임 중복 여부 확인
    @GetMapping("/check-nickname")
    public ResponseEntity<Boolean> checkDuplicateNickName(@RequestParam String nickName) {
        Boolean isNickNameAvailable = userService.checkDuplicateNickName(nickName);
        return ResponseEntity.ok(isNickNameAvailable);
    }
}
