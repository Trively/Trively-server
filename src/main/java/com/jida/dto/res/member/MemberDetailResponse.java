package com.jida.dto.res.member;

import org.springframework.http.ResponseEntity;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberDetailResponse extends BaseResponse{
	private MemberDetailResponseDto data;
	
	private MemberDetailResponse(Boolean success, String message, MemberDetailResponseDto memberResponseDto) {
		super(success, message);
		this.data = memberResponseDto;
	}
	
	public static ResponseEntity<MemberDetailResponse> newResponse(SuccessCode code, MemberDetailResponseDto data){
		MemberDetailResponse response = new MemberDetailResponse(true, code.getMessage(),data);
		return new ResponseEntity(response, code.getStatus());
	}
	
}
