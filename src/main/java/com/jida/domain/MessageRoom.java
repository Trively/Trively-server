package com.jida.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class MessageRoom {
    private long roomId;
    private Member sender;
    private Member receiver;
    private LocalDateTime createdAt;

    public static MessageRoom createMessageRoom(Member sender, Member receiver){
        MessageRoom messageRoom = new MessageRoom();
        messageRoom.sender = sender;
        messageRoom.receiver = receiver;
        return messageRoom;
    }

}
