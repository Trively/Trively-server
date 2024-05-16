package com.jida.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor

public enum ExceptionCode {
	/* 400 - 잘못된 요청 */

    /* 401 - 인증 실패 */
    USER_NOT_FOUND(UNAUTHORIZED, "로그인 유저 정보가 없습니다."),
    // token 관련
    WRONG_TYPE_TOKEN(UNAUTHORIZED, "잘못된 JWT 서명을 가진 토큰입니다."),
    EXPIRED_TOKEN(UNAUTHORIZED, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_TOKEN(UNAUTHORIZED, "지원되지 않는 JWT 토큰입니다."),
    WRONG_TOKEN(UNAUTHORIZED, "JWT 토큰이 잘못되었습니다."),
    UNKNOWN_ERROR(UNAUTHORIZED, "알 수 없는 요청 인증 에러! 헤더에 토큰을 넣어 보냈는지 다시 한 번 확인해보세요."),
    ACCESS_DENIED(UNAUTHORIZED, "접근이 거절되었습니다."),

    /* 403 - 허용되지 않은 접근 */
    FORBIDDEN_ACCESS(FORBIDDEN, "허용되지 않은 접근입니다."),
    RE_COMMENT_ONLY(FORBIDDEN, "답글의 답글은 불가능 합니다."),
    COMMENT_CANT_DELETE(FORBIDDEN, "해당 댓글의 작성자만 삭제 가능합니다."),

    /* 404 - 찾을 수 없는 리소스 */
    POST_NOT_FOUND(NOT_FOUND, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(NOT_FOUND, "댓글을 찾을 수 없습니다.");

    /* 409 - 중복된 리소스 */
  
    private final HttpStatus status;
    private final String message;
}
