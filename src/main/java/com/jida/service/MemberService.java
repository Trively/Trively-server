package com.jida.service;

import com.jida.domain.Member;
import com.jida.dto.req.MemberSaveRequestDto;
import com.jida.dto.res.member.MemberDetailResponseDto;


public interface MemberService {
	void joinMember(MemberSaveRequestDto memberSaveRequestDto);
	Member findById(String memberId);
}
