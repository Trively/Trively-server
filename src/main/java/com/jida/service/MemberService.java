package com.jida.service;

import java.util.Map;

import com.jida.domain.Member;

public interface MemberService {
	Member loginMember(Map<String, String> map) throws Exception;

	void joinMember(Member memberDto) throws Exception;
}
