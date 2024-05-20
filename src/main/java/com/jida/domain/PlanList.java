package com.jida.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlanList {
    private Long planListId;
    private Member member;
    private LocalDateTime createdAt;
    private String title;

    @Builder
    public PlanList(Long planListId, Member member, LocalDateTime createdAt, String title) {
        this.planListId = planListId;
        this.member = member;
        this.createdAt = createdAt;
        this.title = title;
    }
}
