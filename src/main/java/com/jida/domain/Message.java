package com.jida.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Message {
    private long messageId;
    private MessageRoom messageRoom;
    private Member member;
    private String content;
    private String createdAt;

    public static Message createMessage(MessageRoom messageRoom, Member member, String content){
        Message message = new Message();
        message.messageRoom = messageRoom;
        message.member = member;
        message.content = content;
        return message;
    }

}
