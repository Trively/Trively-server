package com.jida.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jida.constants.ExceptionCode;
import com.jida.constants.SuccessCode;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BaseResponse {
	
	private Boolean success;
	private String message;
	private LocalDateTime timestamp = LocalDateTime.now();
	
	public BaseResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}
	
	public static BaseResponse of(Boolean success, String message) {
		return new BaseResponse(success, message);
	}

	public static ResponseEntity<BaseResponse> toCustomErrorResponse(ExceptionCode exceptionCode) {
	        return ResponseEntity
	                .status(exceptionCode.getStatus())
	                .body(BaseResponse.of(false, exceptionCode.getMessage()));
    }

    public static ResponseEntity<BaseResponse> toBasicErrorResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(BaseResponse.of(false, message));
    }
    

	public static ResponseEntity<BaseResponse> toSuccessResponse(SuccessCode successCode) {
		return ResponseEntity
				.status(successCode.getStatus())
				.body(BaseResponse.of(true, successCode.getMessage()));
	}
}
