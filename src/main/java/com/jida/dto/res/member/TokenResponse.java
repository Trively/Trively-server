package com.jida.dto.res.member;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;

@Getter
public class TokenResponse extends BaseResponse {
    private TokenDto data;

    public TokenResponse(Boolean success, String message, TokenDto data){
        super(success, message);
        this.data = data;
    }
}
