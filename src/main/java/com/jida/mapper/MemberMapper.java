package com.jida.mapper;

import com.jida.dto.res.member.MemberDetailResponseDto;
import org.apache.ibatis.annotations.Mapper;

import com.jida.domain.Member;
import com.jida.dto.req.MemberRequestDto;
import com.jida.dto.req.MemberSaveRequestDto;

import java.util.Map;
import java.util.Optional;

@Mapper
public interface MemberMapper {
	Member loginMember(Map<String, String> map);
	Member findById(long memberId); //pk로 찾기
	void joinMember(Member member);
	Member findByCustomId(String id); //아이디로 찾기
	void saveRefreshToken(Map<String, String> map);
	Object getRefreshToken(String id);
	void deleteRefreshToken(Map<String, String> map);
	long memberIdById(String id); //아이디를 pk로 변환
}
