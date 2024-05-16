package com.jida.domain;

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
	private Authority authority;

	public static Member createMember(String id, String password, String email, String nickname, Authority authority){
		Member member = new Member();
		member.id = id;
		member.password = password;
		member.email = email;
		member.nickname = nickname;
		member.authority = authority;
		return member;
	}
}
