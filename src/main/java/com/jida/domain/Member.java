package com.jida.domain;

import com.jida.dto.res.member.MemberDetailResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Member {
	private long memberId;
	private String id;
	private String password;
	private String email;
	private String nickname;
	private String refreshToken;

	public static Member createMember(String id, String password, String email, String nickname){
		Member member = new Member();
		member.id = id;
		member.password = password;
		member.email = email;
		member.nickname = nickname;
		return member;
	}

}
