package com.jida.domain;

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

    public static PlanList createPlanList(Member member) {
        PlanList planList = new PlanList();
        planList.member = member;
        return planList;
    }
}
