package com.jida.service;

import java.util.Map;

import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;
import com.jida.dto.res.member.MemberDetailResponseDto;

public interface MemberService {
	MemberDetailResponseDto loginMember(MemberRequestDto memberRequestDto);
	void joinMember(MemberSaveRequestDto memberSaveRequestDto);
}
