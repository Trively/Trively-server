package com.jida.service;


import com.jida.dto.res.member.MemberDetailResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;
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

	@Override
	public void joinMember(MemberSaveRequestDto memberSaveRequestDto){
		Member member = memberSaveRequestDto.toMember(memberSaveRequestDto);
		memberMapper.joinMember(member);
	}

	@Override
	public MemberDetailResponseDto loginMember(MemberRequestDto memberRequestDto) {
		Map<String, String> map = new HashMap<>();
		map.put("id", memberRequestDto.getId());
		map.put("password", memberRequestDto.getPassword());
        Member member = memberMapper.loginMember(map);
		return MemberDetailResponseDto.of(member);
    }

	@Override
	public MemberDetailResponseDto findById(String memberId) {
		Member member = memberMapper.findById(memberId);
		return MemberDetailResponseDto.of(member);
	}

	@Override
	public void saveRefreshToken(String id, String refreshToken){
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", id);
		map.put("token", refreshToken);
		memberMapper.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String id){
		return memberMapper.getRefreshToken(id);
	}

	@Override
	public void deleteRefreshToken(String id)  {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userId", id);
		map.put("token", null);
		memberMapper.deleteRefreshToken(map);
	}


}
