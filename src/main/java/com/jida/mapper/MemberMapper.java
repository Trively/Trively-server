package com.jida.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jida.domain.Member;
import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;

import java.util.Optional;

@Mapper
public interface MemberMapper {
	Member loginMember(MemberRequestDto memberRequestDto);
	void joinMember(Member member);
	Member findById(String memberId);
}
