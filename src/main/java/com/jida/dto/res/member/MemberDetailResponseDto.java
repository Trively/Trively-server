package com.jida.dto.res.member;

import com.jida.domain.Member;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDetailResponseDto {
	private String id;
	private String nickname;
	
	private MemberDetailResponseDto(Member entity) {
		this.id = entity.getId();
		this.nickname = entity.getNickname();
	}
	public static MemberDetailResponseDto of(Member entity) {
		return new MemberDetailResponseDto(entity);
	}
}
