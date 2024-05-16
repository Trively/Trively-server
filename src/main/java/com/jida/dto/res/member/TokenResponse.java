package com.jida.dto.res.member;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import org.springframework.http.ResponseEntity;

public class TokenResponse extends BaseResponse {
    private TokenDto data;
    private TokenResponse(Boolean success, String message, TokenDto data){
        super(success, message);
        this.data = data;
    }
    public static ResponseEntity<TokenResponse> toResponse(SuccessCode code, TokenDto data){
        TokenResponse response = new TokenResponse(true, code.getMessage(), data);
        return new ResponseEntity<>(response, code.getStatus());
    }
}
