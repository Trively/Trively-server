package com.jida.controller;

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
	
	@PostMapping()
	public ResponseEntity<MemberResponse> join(@RequestBody MemberSaveRequestDto memberSaveRequestDto){
		memberService.joinMember(memberSaveRequestDto);
		return MemberResponse.newResponse(SIGNUP_SUCCESS);
	}
	
	@PostMapping("/login")
	public ResponseEntity<MemberDetailResponse> login(@RequestBody MemberRequestDto memberRequestDto){
		MemberDetailResponseDto responseDto = memberService.loginMember(memberRequestDto);
		return MemberDetailResponse.newResponse(LOGIN_SUCCESS, responseDto);
	}
}
