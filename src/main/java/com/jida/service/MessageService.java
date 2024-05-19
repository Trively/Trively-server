package com.jida.service;

import com.jida.dto.req.MessageRequestDto;
import com.jida.dto.res.message.MessageRoomResponseDto;
import com.jida.dto.res.message.MessageSendResponseDto;
import com.jida.mapper.MemberMapper;

public interface MessageService {
    MessageSendResponseDto sendMessage(MessageRequestDto messageRequestDto, long sendMemberId, long receiveMemberId);
    void replyMessage(MessageRequestDto messageRequestDto, long roomId, long sendMemberId);
    MessageRoomResponseDto showRoomList(long memberId);
}
