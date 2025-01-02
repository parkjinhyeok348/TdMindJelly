package com.server.tdMindJelly.user;

import com.server.tdMindJelly.user.DTO.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 새로운 유저 생성
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody UserSaveReqDTO userSaveReqDTO) {
        Users createdUsers = userService.createUser(userSaveReqDTO);
        return ResponseEntity.ok(createdUsers);
    }

    // 유저 정보 업데이트를 위한 프로필 정보 출력
    @GetMapping("/{userId}/profile")
    public ResponseEntity<UserUpdateResDTO> getUserProfile(@PathVariable Long userId) {
        UserUpdateResDTO userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    // 유저 정보 업데이트
    @PutMapping("/{userId}")
    public ResponseEntity<Users> updateUser(
            @PathVariable Long userId,
            @RequestBody UserUpdateReqDTO reqDTO) {
        Users updatedUsers = userService.updateUser(userId, reqDTO);
        return ResponseEntity.ok(updatedUsers);
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

    // 사용자 이름, 전화번호로 이메일 조회
    @PostMapping("/find-email")
    public ResponseEntity<String> findEmail(@RequestParam String username, @RequestParam String mobilePhoneNumber) {
        String email = userService.findEmail(username,mobilePhoneNumber);
        return ResponseEntity.ok(email);
    }

    // 비밀번호 찾기 (실패시 임시 비밀번호 전송)
    @PostMapping("/find-password")
    public ResponseEntity<String> findPassword(@RequestBody FindPasswordReqDTO reqDTO) {
        try {
            userService.findPassword(reqDTO);
            return ResponseEntity.ok("Temporary password has been sent to your email."); // 성공 시 메시지
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found."); // 사용자 없음
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the request."); // 기타 에러
        }
    }

    //로그인 (아이디와 비밀번호로 계정 진위 확인)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginReqDTO reqDTO) {
        String token = userService.login(reqDTO);
        return ResponseEntity.ok(token);
    }

    // 사용자 삭제
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable("userId")Long userId){
        userService.deleteUser(userId);
    }
}
