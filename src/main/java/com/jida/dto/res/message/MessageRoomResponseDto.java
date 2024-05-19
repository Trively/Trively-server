package com.jida.dto.res.message;

import com.jida.domain.Member;
import com.jida.domain.Message;
import com.jida.domain.MessageRoom;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class MessageRoomResponseDto {
    private List<RoomList> rooms;
    private MessageRoomResponseDto (List<RoomList> rooms){
        this.rooms = rooms;
    }
    public static MessageRoomResponseDto of(List<RoomList> rooms){
        return new MessageRoomResponseDto(rooms);
    }
    @Getter
    @NoArgsConstructor
    public static class RoomList implements Comparable<RoomList>{
        private long roomId;
        private String id;
        private String recentContent;
        private LocalDateTime recentDateTime;

        public static RoomList of(MessageRoom messageRoom, Member member, Message message){
            RoomList roomList = new RoomList();
            roomList.roomId = messageRoom.getRoomId();
            roomList.id =  member.getId();
            roomList.recentContent = message.getContent();
            roomList.recentDateTime = message.getCreatedAt();
            return roomList;
        }

        @Override
        public int compareTo(RoomList o) {
            if(this.recentDateTime.isBefore(o.recentDateTime)) return 1;
            else return -1;
        }
    }
}
