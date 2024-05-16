package com.jida.service;


import com.jida.dto.res.member.TokenDto;
import com.jida.jwt.TokenProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
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
	private final TokenProvider tokenProvider;
	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManagerBuilder authenticationManagerBuilder;

	@Override
	public TokenDto loginMember(MemberRequestDto memberRequestDto){
		Member member = memberMapper.loginMember(memberRequestDto);
		//1. AuthenticationToken 생성
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(memberRequestDto.getId(), memberRequestDto.getPassword());

		//2. 검증 이뤄짐
		Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

		//3. 인증 정보 기반으로 JWT 토큰 생성
		TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

		//4. refreshToken 저장
		member.setRefreshToken(tokenDto.getRefreshToken());

		//5. 토큰 발급
		return tokenDto;
	}

	@Override
	public void joinMember(MemberSaveRequestDto memberSaveRequestDto){
		Member member = memberSaveRequestDto.toMember(memberSaveRequestDto);
		member.setPassword(passwordEncoder.encode(member.getPassword()));
		memberMapper.joinMember(member);
	}

}
