package com.jida.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlanList {
    private Long planListId;
    private Member member;
    private LocalDateTime createdAt;
}
