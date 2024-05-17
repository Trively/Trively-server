package com.jida.dto.res.member;

import com.jida.domain.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDetailResponseDto {

	private long memberId;
	private String id;
	private String password;
	private String email;
	private String nickname;
	private String refreshToken;
	
	private MemberDetailResponseDto(Member entity) {
		this.memberId = entity.getMemberId();
		this.id = entity.getId();
		this.password = entity.getPassword();
		this.email = entity.getEmail();
		this.nickname = entity.getNickname();
		this.refreshToken = entity.getRefreshToken();
	}
	public static MemberDetailResponseDto of(Member entity) {
		return new MemberDetailResponseDto(entity);
	}
}
