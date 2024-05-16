package com.jida.service;

import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;
import com.jida.dto.res.member.TokenDto;

public interface MemberService {
	void joinMember(MemberSaveRequestDto memberSaveRequestDto);
}
