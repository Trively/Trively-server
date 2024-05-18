package com.jida.controller;

import com.jida.domain.Member;
import com.jida.dto.res.member.*;
import com.jida.dto.res.post.PostListResponse;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.service.PostService;
import com.jida.util.JWTUtil;
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

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
			@RequestBody MemberRequestDto memberRequestDto) {
		log.debug("login user : {}", memberRequestDto);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			MemberDetailResponseDto loginMember = memberService.loginMember(memberRequestDto);
			if(loginMember != null) {
				String accessToken = jwtUtil.createAccessToken(loginMember.getId());
				String refreshToken = jwtUtil.createRefreshToken(loginMember.getId());
				log.debug("access token : {}", accessToken);
				log.debug("refresh token : {}", refreshToken);

//				발급받은 refresh token 을 DB에 저장.
				memberService.saveRefreshToken(loginMember.getId(), refreshToken);

//				JSON 으로 token 전달.
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);

				status = HttpStatus.CREATED;
			} else {
				resultMap.put("message", "아이디 또는 패스워드를 확인해 주세요.");
				status = HttpStatus.UNAUTHORIZED;
			}

		} catch (Exception e) {
			log.debug("로그인 에러 발생 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") String userId,
			HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		if (jwtUtil.checkToken(request.getHeader("Authorization"))) {
			log.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				MemberDetailResponseDto memberDetailResponseDto = memberService.findByCustomId(userId);
				resultMap.put("userInfo", memberDetailResponseDto);
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

	@GetMapping("/logout/{userId}")
	public ResponseEntity<?> removeToken(@PathVariable ("userId") String userId) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			memberService.deleteRefreshToken(userId);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody MemberDetailResponseDto memberDetailResponseDto, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refreshToken");
		log.debug("token : {}, memberDto : {}", token, memberDetailResponseDto);
		if (jwtUtil.checkToken(token)) {
			if (token.equals(memberService.getRefreshToken(memberDetailResponseDto.getId()))) {
				String accessToken = jwtUtil.createAccessToken(memberDetailResponseDto.getId());
				log.debug("token : {}", accessToken);
				log.debug("정상적으로 access token 재발급!!!");
				resultMap.put("access-token", accessToken);
				status = HttpStatus.CREATED;
			}
		} else {
			log.debug("refresh token 도 사용 불가!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}

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
