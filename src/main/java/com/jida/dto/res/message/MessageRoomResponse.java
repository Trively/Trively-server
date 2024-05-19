package com.jida.dto.res.message;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class MessageRoomResponse extends BaseResponse {
    private MessageRoomResponseDto data;

    private MessageRoomResponse(Boolean success, String message, MessageRoomResponseDto data){
        super(success, message);
        this.data = data;
    }

    public static MessageRoomResponse of(Boolean success, String message, MessageRoomResponseDto data) {
        return new MessageRoomResponse(success, message, data);
    }
    public static ResponseEntity<MessageRoomResponse> newResponse(SuccessCode code, MessageRoomResponseDto data){
        MessageRoomResponse response = MessageRoomResponse.of(true, code.getMessage(),data);
        return new ResponseEntity<>(response,code.getStatus());
    }
}
