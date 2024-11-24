package com.ssafy.trip.controller.member;


import com.ssafy.trip.dto.member.*;
import com.ssafy.trip.service.member.MemberService;
import com.ssafy.trip.util.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
@Tag(name = "회원 컨트롤러", description = "로그인 로그아웃, 토큰처리등 회원의 인증관련 처리하는 클래스.")
public class MemberController {

    private final MemberService memberService;
    private final JWTUtil jwtUtil;

    @Operation(summary = "회원가입", description = "회원가입")
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody JoinMemberRequest request) throws Exception {
        int res = memberService.join(request);
        Map<String, Object> result = new HashMap<>();
        if(res == 1) {
            result.put("message", "회원가입에 성공했습니다.");
            return new ResponseEntity<>(result, HttpStatus.CREATED);//201
        } else {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);//204
        }
    }

    @Operation(summary = "로그인", description = "아이디와 비밀번호를 이용하여 로그인 처리, 정상적으로 로그인 처리 시 refreshToken 이 발급된다.")
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @Parameter(description = "로그인 시 필요한 회원정보(아이디, 비밀번호).", required = true) LoginMemberRequest request) {
        log.debug("login user : {}", request);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            MemberDto loginUser = memberService.login(request);
            if(loginUser != null) {
                String accessToken = jwtUtil.createAccessToken(loginUser.getMemberId());
                String refreshToken = jwtUtil.createRefreshToken(loginUser.getMemberId());
                log.debug("access token : {}", accessToken);
                log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token 을 DB에 저장.
                memberService.saveRefreshToken(loginUser.getMemberId(), refreshToken);

//				JSON 으로 token 전달.
                resultMap.put("access-token", accessToken);
                resultMap.put("refresh-token", refreshToken);

                status = HttpStatus.CREATED;
            } else {
                resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
                status = HttpStatus.UNAUTHORIZED;
            }

        } catch (Exception e) {
            e.printStackTrace();
            log.debug("로그인 에러 발생 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 로그아웃
    @Operation(summary = "로그아웃", description = "회원 정보를 담은 Token 을 제거한다.")
    @GetMapping("/logout")
    public ResponseEntity<?> removeToken(@RequestAttribute("userId") String memberId) {
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status = HttpStatus.ACCEPTED;
        try {
            memberService.deleteRefreshToken(memberId);
            status = HttpStatus.OK;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("로그아웃 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }

    // 회원정보수정
    @Operation(summary = "회원정보수정", description = "회원정보(이룸, 이메일, 주소)를 수정한다.")
    @PatchMapping("/update")
    public ResponseEntity<?> updateMember(@RequestAttribute("userId") String memberId, @RequestBody @Parameter(description = "수정할 회원의 정보들 ", required = true) UpdateMemberRequest request) {
        try {
            memberService.updateMember(memberId, request);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug("회원정보수정 에러 발생 : {}", e);
            Map<String, Object> result = new HashMap<>();
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }       
    }

    // 회원 탈퇴
    @Operation(summary = "회원탈퇴", description = "해당 회원을 탈퇴한다.")
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteMember(@RequestAttribute("userId") String  memberId) {
        Map<String, Object> result = new HashMap<>();
        try {
            memberService.deleteMember(memberId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            result.put("message", e.getMessage());
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary = "회원인증", description = "회원 정보를 담은 Token 을 반환한다.")
    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getInfo(@RequestAttribute("userId") String memberId) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            InfoMemberResponse infoMember = memberService.memberInfo(memberId);
            resultMap.put("userInfo", infoMember);
            return new ResponseEntity<>(resultMap, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("정보조회 실패 : {}", e);
            resultMap.put("message", e.getMessage());
            return new ResponseEntity<>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // 아이디조회
    @PostMapping("/idCheck")
    public ResponseEntity<?> idCheck(@RequestBody String memberId) {
        try {
            int cnt = memberService.idCheck(memberId);
            log.info("memberId : {}, cnt : {}", memberId, cnt);
            return new ResponseEntity<Integer>(cnt, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
    }
}
