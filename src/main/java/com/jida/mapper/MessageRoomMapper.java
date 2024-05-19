package com.jida.mapper;

import com.jida.domain.Member;
import com.jida.domain.MessageRoom;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MessageRoomMapper {
    void saveMessageRoom(MessageRoom messageRoom);
    MessageRoom findById(long roomId);
    Optional<MessageRoom> findByMembers(long sendMemberId, long receiveMemberId);
    List<MessageRoom> findByMember(long memberId);

}
