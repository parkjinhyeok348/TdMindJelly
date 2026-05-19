package com.server.tdMindJelly.user;

import java.security.SecureRandom;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.server.tdMindJelly.user.DTO.FindPasswordReqDTO;
import com.server.tdMindJelly.user.DTO.UserLoginReqDTO;
import com.server.tdMindJelly.user.DTO.UserResDTO;
import com.server.tdMindJelly.user.DTO.UserSaveReqDTO;
import com.server.tdMindJelly.user.DTO.UserUpdateReqDTO;
import com.server.tdMindJelly.user.DTO.UserUpdateResDTO;
import com.server.tdMindJelly.user.JWT.AuthenticatedUserService;
import com.server.tdMindJelly.user.JWT.JwtUtil;
import com.server.tdMindJelly.user.JWT.PasswordEncryptService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncryptService passwordEncryptService;
    private static final SecureRandom secureRandom = new SecureRandom();
    private final JavaMailSender mailSender;
    private final AuthenticatedUserService authenticatedUserService;

    //새로운 유저 생성
    public Users createUser(UserSaveReqDTO userSaveReqDTO){
        String encodedPassword = passwordEncryptService.encodePassword(userSaveReqDTO.getPassword()); // 비밀번호 암호화
        Users users = userSaveReqDTO.toEntity(encodedPassword);
        return userRepository.save(users);
    }
    
    //유저 정보 업데이트를 위한 프로필 정보 출력
    public UserUpdateResDTO getUserProfile(Long userId){
        authenticatedUserService.assertCurrentUser(userId);
        Users users = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new UserUpdateResDTO(users.getNickName(), users.getProfileImage(), users.getIsMarketing());
    }

    // 유저 정보 업데이트
    public Users updateUser(Long userId, UserUpdateReqDTO reqDTO){
        authenticatedUserService.assertCurrentUser(userId);
        Users users = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        users.updateUser(reqDTO.getNickName(),reqDTO.getProfileImage(),reqDTO.getIsMarketing());
        return users;
    }

    // 사용자 아이디로 유저 상세정보 출력
    public UserResDTO getUserById(Long userId){
        authenticatedUserService.assertCurrentUser(userId);
        Users entity = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return new UserResDTO(entity);
    }

    // 이메일 중복 여부 확인 (true = 사용 가능, false = 이미 사용 중)
    public Boolean checkDuplicateEmail(String email) {
        return !userRepository.existsByEmail(email);
    }

    // 전화번호 중복 여부 확인 (true = 사용 가능, false = 이미 사용 중)
    public Boolean checkDuplicateMobilePhoneNumber(String mobilePhoneNumber) {
        return !userRepository.existsByMobilePhoneNumber(mobilePhoneNumber);
    }

    //닉네임 중복 여부 확인
    public Boolean checkDuplicateNickName(String nickName) {
        return !userRepository.existsByNickName(nickName);
    }
    
    // 유저 계정 삭제
    public void deleteUser(Long userId){
        authenticatedUserService.assertCurrentUser(userId);
        Users users = userRepository.findById(userId).orElseThrow(() -> new EntityNotFoundException("User not found"));
        userRepository.deleteById(userId);
    }

    // 로그인
    public String login(UserLoginReqDTO reqDTO) {
        Users users = userRepository.findByEmail(reqDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncryptService.matchesPassword(reqDTO.getPassword(), users.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return jwtUtil.generateToken(reqDTO.getEmail(), users.getUserId()); // JWT 생성
    }

    // 비밀번호 찾기
    public void findPassword(FindPasswordReqDTO reqDTO) {
        Users users = userRepository.findByUserNameAndEmailAndMobilePhoneNumber(reqDTO.getUserName(), reqDTO.getEmail(), reqDTO.getMobilePhoneNumber())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String tmpPassword = RandomStringUtils.random(8, 0, 0, true, true, null, secureRandom);

        String encodedPassword = passwordEncryptService.encodePassword(tmpPassword);
        users.updatePassword(encodedPassword);
        userRepository.save(users);
        sendMail(reqDTO.getEmail(), tmpPassword);
    }

    // 이메일 찾기
    public String findEmail(String username, String mobilePhoneNumber) {
        Users users = userRepository.findByUserNameAndMobilePhoneNumber(username,mobilePhoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return users.getEmail(); // 이메일 반환
    }

    // 임시 비밀번호 메일 전송
    public void sendMail(String email, String tmpPassword) {
        try {

            MimeMessage mimeMessage = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setTo(email);
            helper.setSubject("[오늘의 마음젤리] 임시 비밀번호 전송 드립니다.");
            helper.setText("임시 비밀번호: " + tmpPassword +
                    "\n\n위 임시 비밀번호로 로그인 후, 마이페이지에서 비밀번호를 변경해주세요.\n", true);

            // 메일 전송
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new RuntimeException("메일 전송 중 오류 발생", e);
        }
    }

}
