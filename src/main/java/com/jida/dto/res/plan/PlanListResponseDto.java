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
    private String title;
    private List<PlanLists> planLists;

    private PlanListResponseDto(String title, List<PlanLists> planLists) {
        this.title = title;
        this.planLists = planLists;
    }

    public static PlanListResponseDto of(String title, List<PlanLists> planLists) {
        return new PlanListResponseDto(title, planLists);
    }

    @Getter
    @NoArgsConstructor
    public static class PlanLists{
        private AttractionList attractionList;
        private long planListId;
        private String planDate;
        private int orders;
        private boolean open;

        public static PlanLists of(Plan plan) {
            PlanLists planLists = new PlanLists();
            planLists.attractionList = AttractionList.of(plan.getAttraction());
            planLists.planListId = plan.getPlanList().getPlanListId();
            planLists.planDate = String.valueOf(plan.getPlanDate());
            planLists.orders = plan.getOrders();
            planLists.open = plan.isOpen();
            return planLists;
        }
    }
}
