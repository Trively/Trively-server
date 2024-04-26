package com.jida.dto.res.post;

import org.springframework.http.ResponseEntity;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDetailResponse extends BaseResponse{
	PostDetailResponseDto data;
	
	private PostDetailResponse(Boolean success, String message, PostDetailResponseDto data) {
		super(success, message);
		this.data = data;
	}
	
	public static PostDetailResponse of(Boolean success, String message, PostDetailResponseDto data) {
		return new PostDetailResponse(success, message, data);
	}
	
	public static ResponseEntity<PostDetailResponse> newResponse(SuccessCode code, PostDetailResponseDto data) {
		PostDetailResponse response = PostDetailResponse.of(true, code.getMessage(), data);
		return new ResponseEntity<>(response, code.getStatus());
	}
}
