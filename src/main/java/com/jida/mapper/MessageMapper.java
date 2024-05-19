package com.jida.mapper;

import com.jida.domain.Message;
import com.jida.domain.MessageRoom;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MessageMapper {
    void saveMessage(Message message);
    Message findFirstByMessageRoom(long roomId);
}
