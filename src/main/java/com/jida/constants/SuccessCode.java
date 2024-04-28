package com.jida.constants;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum SuccessCode {
	
	SIGNUP_SUCCESS(OK, "회원가입을 완료했습니다."),
	LOGIN_SUCCESS(OK, "로그인에 성공했습니다."),
	//Attraction
	ATTRACTION_LIST_READ_SUCCESS(OK, "여행지 리스트 조회를 성공했습니다."),
	//Board
	BOARD_READ_SUCCESS(OK, "게시판 전체 조회를 성공했습니다."),
	POST_SAVE_SUCCESS(OK, "게시글 저장을 완료했습니다."),
	POST_UPDATE_SUCCESS(OK, "게시글 수정을 완료했습니다."),
	POST_DELETE_SUCCESS(OK, "게시글 삭제를 완료했습니다."),
	POST_DETAIL_SUCCESS(OK, "게시글 상세 조회를 성공했습니다."),
	POST_LIST_SUCCESS(OK, "게시글 전체 조회를 성공했습니다.");

	private final HttpStatus status;
	private final String message;
}
