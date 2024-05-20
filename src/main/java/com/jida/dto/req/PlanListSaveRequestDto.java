package com.jida.dto.req;

import com.jida.domain.Member;
import com.jida.domain.PlanList;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanListSaveRequestDto {
    private Member member;
    private String title;

    private PlanListSaveRequestDto(Member member, String title) {
        this.member = member;
        this.title = title;
    }

    public static PlanListSaveRequestDto of(Member member, String title) {
        return new PlanListSaveRequestDto(member, title);
    }

    public PlanList ToEntity(PlanListSaveRequestDto dto) {
        return PlanList.builder()
                .member(dto.getMember())
                .title(dto.getTitle())
                .build();
    }
}
