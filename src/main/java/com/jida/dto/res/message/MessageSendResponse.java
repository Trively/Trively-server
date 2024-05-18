package com.jida.dto.res.message;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class MessageSendResponse extends BaseResponse {
    private MessageSendResponseDto data;

    private MessageSendResponse(Boolean success, String message, MessageSendResponseDto data){
        super(success, message);
        this.data = data;
    }
    public static MessageSendResponse of(Boolean success, String message, MessageSendResponseDto data){
        return new MessageSendResponse(success, message, data);
    }
    public static ResponseEntity<MessageSendResponse> newResponse(SuccessCode code, MessageSendResponseDto data){
        MessageSendResponse response = MessageSendResponse.of(true, code.getMessage(), data);
        return new ResponseEntity<>(response, code.getStatus());
    }


}
