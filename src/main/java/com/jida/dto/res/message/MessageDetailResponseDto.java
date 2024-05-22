package com.jida.dto.res.message;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jida.domain.Message;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MessageDetailResponseDto {
    List<MessageDto> messages;

    private MessageDetailResponseDto(List<MessageDto> messages){
        this.messages = messages;
    }
    public static MessageDetailResponseDto of(List<MessageDto> messages){
        return new MessageDetailResponseDto(messages);
    }
    @Getter
    @NoArgsConstructor
    public static class MessageDto{
        private long messageId;
        private String content;
        @JsonFormat(pattern = "yyyy/MM/dd HH:mm", timezone = "Asia/Seoul")
        private LocalDateTime createdAt;
        private boolean sender;
        private Long planlistId;

        public static MessageDto of(Message message, boolean sender){
            MessageDto messageDto = new MessageDto();
            messageDto.messageId = message.getMessageId();
            messageDto.content = message.getContent();
            messageDto.createdAt = message.getCreatedAt();
            messageDto.sender = sender;
            messageDto.planlistId = message.getPlanlistId();
            return messageDto;
        }

    }

}
