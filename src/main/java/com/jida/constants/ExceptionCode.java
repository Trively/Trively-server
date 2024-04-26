package com.jida.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor

public enum ExceptionCode {
	/* 400 - 잘못된 요청 */

    /* 401 - 인증 실패 */
    USER_NOT_FOUND(UNAUTHORIZED, "로그인 유저 정보가 없습니다.");

    /* 403 - 허용되지 않은 접근 */
  
    /* 404 - 찾을 수 없는 리소스 */

    /* 409 - 중복된 리소스 */
  
    private final HttpStatus status;
    private final String message;
}
