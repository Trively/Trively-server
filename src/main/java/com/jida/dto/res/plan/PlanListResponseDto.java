package com.jida.dto.res.plan;

import com.jida.domain.Plan;
import com.jida.dto.res.attraction.AttractionListResponseDto.AttractionList;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@NoArgsConstructor
public class PlanListResponseDto {
    private List<PlanLists> planLists;

    private PlanListResponseDto(List<PlanLists> planLists) {
        this.planLists = planLists;
    }

    public static PlanListResponseDto of(List<PlanLists> planLists) {
        return new PlanListResponseDto(planLists);
    }

    @Getter
    @NoArgsConstructor
    public static class PlanLists{
        private AttractionList attractionList;
        private String planDate;
        private int orders;
        private boolean open;

        public static PlanLists of(Plan plan) {
            PlanLists planLists = new PlanLists();
            planLists.attractionList = AttractionList.of(plan.getAttraction());
            planLists.planDate = plan.getPlanDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            planLists.orders = plan.getOrders();
            planLists.open = plan.isOpen();
            return planLists;
        }
    }
}
