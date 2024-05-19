package com.jida.service;

import com.jida.domain.Member;
import com.jida.domain.Message;
import com.jida.domain.MessageRoom;
import com.jida.dto.req.MessageRequestDto;
import com.jida.dto.res.message.MessageRoomResponseDto;
import com.jida.dto.res.message.MessageRoomResponseDto.RoomList;
import com.jida.dto.res.message.MessageSendResponseDto;
import com.jida.exception.CustomException;
import com.jida.mapper.MemberMapper;
import com.jida.mapper.MessageMapper;
import com.jida.mapper.MessageRoomMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.jida.constants.ExceptionCode.MESSAGE_DENIED;
import static java.util.List.of;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    private final MemberMapper memberMapper;
    private final MessageMapper messageMapper;
    private final MessageRoomMapper messageRoomMapper;
    @Override
    public MessageSendResponseDto sendMessage(MessageRequestDto messageRequestDto, long sendMemberId, long receiveMemberId) {
        Member sendMember = memberMapper.findById(sendMemberId);
        Member receiveMember = memberMapper.findById(receiveMemberId);
        if(sendMember == receiveMember || receiveMember == null){
            throw new CustomException(MESSAGE_DENIED);
        }
        Optional<MessageRoom> messageRoom = messageRoomMapper.findByMembers(sendMember.getMemberId(), receiveMember.getMemberId());
        MessageRoom room;
        if(messageRoom.isEmpty()){
            MessageRoom createdMessageRoom = MessageRoom.createMessageRoom(sendMember, receiveMember);

            messageRoomMapper.saveMessageRoom(createdMessageRoom);
            room = createdMessageRoom;
            log.info("Roomid = {}", room.getRoomId());
        }
        else{
            room = messageRoom.get();
        }
        Message message = Message.createMessage(room, sendMember, messageRequestDto.getContent());
        messageMapper.saveMessage(message);
        return MessageSendResponseDto.of(room.getRoomId());
    }

    @Override
    public void replyMessage(MessageRequestDto messageRequestDto, long roomId, long sendMemberId) {
       Member member = memberMapper.findById(sendMemberId);
       MessageRoom messageRoom = messageRoomMapper.findById(roomId);
       if(messageRoom == null){
           throw new CustomException(MESSAGE_DENIED);
       }
       Message message = Message.createMessage(messageRoom, member, messageRequestDto.getContent());
       messageMapper.saveMessage(message);
    }

    @Override
    public MessageRoomResponseDto showRoomList(long memberId) {
        Member member = memberMapper.findById(memberId);
        List<MessageRoom> rooms = messageRoomMapper.findByMember(memberId);
        List<RoomList> roomList = rooms.stream()
                                        .map(messageRoom -> {
                                            Message recent = messageMapper.findFirstByMessageRoom(messageRoom.getRoomId());
                                            return RoomList.of(messageRoom, member, recent);
                                        }).collect(Collectors.toList());
        Collections.sort(roomList);
        return MessageRoomResponseDto.of(roomList);
    }
}
