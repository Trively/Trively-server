package com.jida.dto.res.member;

import com.jida.domain.Authority;
import com.jida.domain.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDetailResponseDto {
	private String id;
	private String password;
	private String email;
	private String nickname;
	private Authority authority;
	
	private MemberDetailResponseDto(Member entity) {
		this.id = entity.getId();
		this.password = entity.getPassword();
		this.email = entity.getEmail();
		this.nickname = entity.getNickname();
		this.authority = entity.getAuthority();
	}
	public static MemberDetailResponseDto of(Member entity) {
		return new MemberDetailResponseDto(entity);
	}
}
