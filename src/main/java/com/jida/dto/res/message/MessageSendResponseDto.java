package com.jida.dto.res.message;

import lombok.Getter;

@Getter
public class MessageSendResponseDto {
    long roomId;
    private MessageSendResponseDto(long roomId){
        this.roomId = roomId;
    }
    public static MessageSendResponseDto of(long roomId) {
        return new MessageSendResponseDto(roomId);
    }
}
