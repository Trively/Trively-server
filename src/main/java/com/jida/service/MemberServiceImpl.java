package com.jida.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jida.domain.Member;
import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;
import com.jida.dto.res.member.MemberDetailResponseDto;
import com.jida.mapper.MemberMapper;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
	private final MemberMapper memberMapper;
	@Override
	public MemberDetailResponseDto loginMember(MemberRequestDto memberRequestDto){
		Member member = memberMapper.loginMember(memberRequestDto);
		return MemberDetailResponseDto.of(member);
	}

	@Override
	public void joinMember(MemberSaveRequestDto memberSaveRequestDto){
		memberMapper.joinMember(memberSaveRequestDto);
	}

}
