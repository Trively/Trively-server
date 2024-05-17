package com.jida.service;

import com.jida.domain.Member;
import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;
import com.jida.dto.res.member.MemberDetailResponseDto;


public interface MemberService {
	void joinMember(MemberSaveRequestDto memberSaveRequestDto);
	MemberDetailResponseDto loginMember(MemberRequestDto memberRequestDto);
	MemberDetailResponseDto findById(String memberId);
	void saveRefreshToken(String memberId, String refreshToken);
	Object getRefreshToken(String memberId) throws Exception;
	void deleteRefreshToken(String memberId) throws Exception;
}
