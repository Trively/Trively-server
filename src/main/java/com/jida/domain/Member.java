package com.jida.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {
	private long memberId;
	private String id;
	private String password;
	private String email;
	private String nickname;
}
