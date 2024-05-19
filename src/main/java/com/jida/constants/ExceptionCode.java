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
    USER_NOT_FOUND(UNAUTHORIZED, "로그인 유저 정보가 없습니다.")

    /* 403 - 허용되지 않은 접근 */,
    ACCESS_DENIED(UNAUTHORIZED, "접근이 거절되었습니다."),
    FORBIDDEN_ACCESS(FORBIDDEN, "허용되지 않은 접근입니다."),
    RE_COMMENT_ONLY(FORBIDDEN, "답글의 답글은 불가능 합니다."),
    COMMENT_CANT_DELETE(FORBIDDEN, "해당 댓글의 작성자만 삭제 가능합니다."),
    POST_CANT_DELETE(FORBIDDEN, "해당 글의 작성자만 삭제 가능합니다."),
    MESSAGE_DENIED(FORBIDDEN, "쪽지를 전송할 수 없습니다."),

    /* 404 - 찾을 수 없는 리소스 */
    POST_NOT_FOUND(NOT_FOUND, "게시글을 찾을 수 없습니다."),
    COMMENT_NOT_FOUND(NOT_FOUND, "댓글을 찾을 수 없습니다."),
    MESSAGE_ROOM_NOT_FOUND(NOT_FOUND, "쪽지룸을 찾을 수 없습니다.");

    /* 409 - 중복된 리소스 */
  
    private final HttpStatus status;
    private final String message;
}
