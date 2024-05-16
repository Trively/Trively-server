package com.jida.dto.req;

import com.jida.domain.Authority;
import com.jida.domain.Member;
import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
public class MemberSaveRequestDto {
	private String id;
	private String password;
	private String email;
	private String nickname;

	public Member toMember(MemberSaveRequestDto memberSaveRequestDto){
		return Member.createMember(memberSaveRequestDto.getId(), memberSaveRequestDto.getPassword(), memberSaveRequestDto.getEmail(), memberSaveRequestDto.getNickname(), Authority.USER);
	}
}
