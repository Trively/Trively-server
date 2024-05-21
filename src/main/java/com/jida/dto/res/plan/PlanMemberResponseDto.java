package com.jida.dto.res.plan;

import com.jida.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
public class PlanMemberResponseDto {
    List<MessageMember> members;

    private PlanMemberResponseDto(List<MessageMember> members) {
        this.members = members;
    }
    public static PlanMemberResponseDto of(List<MessageMember> members) {
        return new PlanMemberResponseDto(members);
    }
    @Getter
    @NoArgsConstructor
    public static class MessageMember {
        private long memberId;
        private String nickname;

        public static MessageMember of(Member member){
            MessageMember messageMember = new MessageMember();
            messageMember.memberId = member.getMemberId();
            messageMember.nickname = member.getNickname();
            return messageMember;
        }
    }
}
