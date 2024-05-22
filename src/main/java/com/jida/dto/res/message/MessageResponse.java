package com.jida.dto.res.message;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import org.springframework.http.ResponseEntity;

public class MessageResponse extends BaseResponse {
    private MessageResponse(Boolean success, String message){
        super(success, message);
    }
    public static MessageResponse of(Boolean success, String message){
        return new MessageResponse(success, message);
    }
    public static ResponseEntity<MessageResponse> newResponse(SuccessCode code){
        return new ResponseEntity<>(MessageResponse.of(true, code.getMessage()), code.getStatus());
    }
}
