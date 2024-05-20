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
	POST_LIST_SUCCESS(OK, "게시글 전체 조회를 성공했습니다."),
	POST_LIKE_SUCCESS(OK, "공감에 성공했습니다."),
	POST_LIKE_CANCELED(OK, "공감 취소에 성공했습니다."),
	POST_SCRAP_SUCCESS(OK, "게시글 스크랩을 성공했습니다."),
	POST_SCRAP_CANCELED(OK, "게시글 스크랩 취소에 성공했습니다."),
	SCRAP_READ_SUCCESS(OK, "스크랩 전체 조회를 성공했습니다."),
	COMMENT_SAVE_SUCCESS(OK, "댓글 저장을 완료했습니다."),
	COMMENT_LIST_SUCCESS(OK, "댓글 조회를 성공했습니다."),
	COMMENT_DELETE_SUCCESS(OK, "댓글 삭제를 성공했습니다."),

	//Message
	MESSAGE_SEND_SUCCESS(OK, "메시지 전송에 성공했습니다."),
	MESSAGE_REPLY_SUCCESS(OK, "메시지 답장에 성공했습니다."),
	MESSAGE_ROOM_READ_SUCCESS(OK, "메시지룸 목록 조회를 성공했습니다."),
	MESSAGE_READ_SUCCESS(OK, "메시지 목록 조회에 성공했습니다."),
	
	//Plan
	PLAN_SAVE_SUCCESS(OK, "여행계획 저장을 완료했습니다."),
	PLAN_SHOW_SUCCESS(OK, "여행계획 조회를 완료했습니다.");


	private final HttpStatus status;
	private final String message;
}
