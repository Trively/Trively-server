package com.jida.dto.res.member;

import org.springframework.http.ResponseEntity;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;

public class MemberResponse extends BaseResponse{
	
	private MemberResponse(Boolean success, String message) {
		super(success, message);
	}
	public static MemberResponse of(Boolean success, String message) {
		return new MemberResponse(success, message);
	}
	public static ResponseEntity<MemberResponse> newResponse(SuccessCode code){
		return new ResponseEntity<>(MemberResponse.of(true, code.getMessage()), code.getStatus());
	}
	
}
