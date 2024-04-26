package com.jida.constants;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum SuccessCode {
	
    SIGNUP_SUCCESS(OK, "회원가입을 완료했습니다."),
    LOGIN_SUCCESS(OK, "로그인에 성공했습니다.");
	
	private final HttpStatus status;
	private final String message;
}
