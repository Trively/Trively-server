package com.jida.controller;

import com.jida.domain.Member;
import com.jida.dto.res.member.*;
import com.jida.dto.res.post.PostListResponse;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.jwt.JWTUtil;
import com.jida.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;

import com.jida.service.MemberService;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import static com.jida.constants.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;
	private final PostService postService;
	private final JWTUtil jwtUtil;

	@PostMapping("join")
	public ResponseEntity<MemberResponse> join(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
		memberService.joinMember(memberSaveRequestDto);
		return MemberResponse.newResponse(SIGNUP_SUCCESS);
	}

	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") String memberId,
			HttpServletRequest request) {
//		logger.debug("userId : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				Member member = memberService.findById(memberId);
				resultMap.put("userInfo", member);
				status = HttpStatus.OK;
			} catch (Exception e) {
				log.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} else {
			log.error("사용 불가능 토큰!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

//	@PostMapping("/login")
//	public String login(@Validated @RequestBody MemberRequestDto memberRequestDto) {
//		String token = jwtUtil.createJwt(memberRequestDto.getId(), memberRequestDto.getPassword(), 1000*60*60L);
////		TokenDto tokenDto = memberService.loginMember(memberRequestDto);
//		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(memberRequestDto.getId(), memberRequestDto.getPassword()));
////		return TokenResponse.toResponse(LOGIN_SUCCESS, tokenDto);
//		return token;
//	}

	@GetMapping("/my-scrap")
	public ResponseEntity<PostListResponse> myScrap(
			@RequestParam(name = "order", defaultValue = "latest") String order,
			@RequestParam(name = "boardId", defaultValue = "0") long boardId,
			@RequestParam(name = "pageIndex", defaultValue = "1") int pageIndex,
			@RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
		PostListResponseDto postListResponseDto = postService.showScrap(order, boardId, pageIndex, pageSize);
		return PostListResponse.newResponse(SCRAP_READ_SUCCESS ,postListResponseDto);
	}
}
