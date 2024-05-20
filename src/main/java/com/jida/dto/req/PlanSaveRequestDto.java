package com.jida.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jida.domain.Attraction;
import com.jida.domain.Plan;
import com.jida.domain.PlanList;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
public class PlanSaveRequestDto {
    private String title;
    private List<Plans> plans;

    @Getter
    @NoArgsConstructor
    public static class Plans{
        @NotNull(message = "여행지 id 값은 필수입니다.")
        private Long attractionId;
        @JsonFormat(pattern = "yyyy-MM-dd")
        private LocalDate planDate;
        private int orders;
    }

    public Plan ToEntity(PlanList planList, Attraction attraction, int orders, LocalDate planDate) {
        return Plan.builder()
                .planList(planList)
                .attraction(attraction)
                .orders(orders)
                .planDate(planDate)
                .build();
    }
}
