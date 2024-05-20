package com.jida.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlanList {
    private Long planListId;
    private Member member;
    private LocalDateTime createdAt;
    private String title;
    private String mainImage;
}
