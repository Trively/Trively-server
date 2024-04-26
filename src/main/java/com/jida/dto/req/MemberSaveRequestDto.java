package com.jida.dto.req;

import lombok.Getter;

@Getter
public class MemberSaveRequestDto {
	private String id;
	private String password;
	private String email;
	private String nickname;
}
