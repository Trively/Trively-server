package com.jida.controller;

import com.jida.dto.res.member.*;
import com.jida.dto.res.post.PostListResponse;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.service.PostService;
import org.springframework.http.ResponseEntity;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;

import com.jida.service.MemberService;

import lombok.RequiredArgsConstructor;
import static com.jida.constants.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/member")
public class MemberController {

	private final MemberService memberService;
	private final PostService postService;

	@PostMapping()
	public ResponseEntity<MemberResponse> join(@RequestBody MemberSaveRequestDto memberSaveRequestDto) {
		memberService.joinMember(memberSaveRequestDto);
		return MemberResponse.newResponse(SIGNUP_SUCCESS);
	}

	@PostMapping("/login")
	public ResponseEntity<TokenResponse> login(@Validated @RequestBody MemberRequestDto memberRequestDto) {
		TokenDto tokenDto = memberService.loginMember(memberRequestDto);
		return TokenResponse.toResponse(LOGIN_SUCCESS, tokenDto);
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
