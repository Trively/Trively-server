package com.jida.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Plan {
    private Long planId;
    private PlanList planList;
    private Attraction attraction;
    private int orders;
    private boolean open;
    private LocalDateTime planDate;
}
