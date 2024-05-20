package com.jida.domain;

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


    public static Plan createPlan(PlanList planList, Attraction attraction, int orders, LocalDate planDate) {
        Plan plan = new Plan();
        plan.planList = planList;
        plan.attraction = attraction;
        plan.orders = orders;
        plan.planDate = planDate;

        return plan;
    }
}
