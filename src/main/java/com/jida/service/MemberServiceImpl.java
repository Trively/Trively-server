package com.jida.service;


import com.jida.dto.res.member.MemberDetailResponseDto;
import com.jida.dto.res.member.TokenDto;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jida.domain.Member;
import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;
import com.jida.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void joinMember(MemberSaveRequestDto memberSaveRequestDto){
		Member member = memberSaveRequestDto.toMember(memberSaveRequestDto);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberMapper.joinMember(member);
	}

	@Override
	public Member findById(String memberId) {
		return memberMapper.findById(memberId);
	}


}
