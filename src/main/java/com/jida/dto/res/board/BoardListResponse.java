package com.jida.dto.res.board;

import org.springframework.http.ResponseEntity;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardListResponse extends BaseResponse{
	BoardListResponseDto data;
	
	private BoardListResponse(Boolean success, String msg, BoardListResponseDto data) {
		super(success, msg);
		this.data = data;
	}
	
	public static BoardListResponse of(Boolean success, String msg, BoardListResponseDto data) {
		return new BoardListResponse(success, msg, data);
	}
	
	public static ResponseEntity<BoardListResponse> newResponse(SuccessCode code, BoardListResponseDto data) {
		BoardListResponse response = BoardListResponse.of(true, code.getMessage(), data);
		return new ResponseEntity(response, code.getStatus());	
	}
}
