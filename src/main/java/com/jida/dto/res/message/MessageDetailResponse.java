package com.jida.dto.res.message;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class MessageDetailResponse extends BaseResponse {
    MessageDetailResponseDto data;

    private MessageDetailResponse(Boolean success, String message, MessageDetailResponseDto data) {
        super(success,message);
        this.data = data;
    }

    public static MessageDetailResponse of(Boolean success, String message, MessageDetailResponseDto data){
        return new MessageDetailResponse(success, message, data);
    }

    public static ResponseEntity<MessageDetailResponse> newResponse(SuccessCode code, MessageDetailResponseDto data){
        MessageDetailResponse response = MessageDetailResponse.of(true, code.getMessage(), data);
        return new ResponseEntity<>(response, code.getStatus());
    }
}
