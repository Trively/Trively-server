package com.jida.controller;

import com.jida.dto.res.post.PostListResponse;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;
import com.jida.dto.res.member.MemberDetailResponse;
import com.jida.dto.res.member.MemberDetailResponseDto;
import com.jida.dto.res.member.MemberResponse;

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
	public ResponseEntity<MemberDetailResponse> login(@RequestBody MemberRequestDto memberRequestDto) {
		MemberDetailResponseDto responseDto = memberService.loginMember(memberRequestDto);
		return MemberDetailResponse.newResponse(LOGIN_SUCCESS, responseDto);
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
