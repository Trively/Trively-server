package com.jida.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class Plan {
    private Long planId;
    private PlanList planList;
    private Attraction attraction;
    private int orders;
    private boolean open;
    private LocalDate planDate;

    @Builder
    public Plan(PlanList planList, Attraction attraction, int orders, LocalDate planDate) {
        this.planList = planList;
        this.attraction = attraction;
        this.orders = orders;
        this.planDate = planDate;
    }
}
