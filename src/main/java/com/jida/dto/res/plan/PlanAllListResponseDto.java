package com.jida.dto.res.plan;

import com.jida.domain.PlanList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PlanAllListResponseDto {
    private List<PlanAllList> planAllLists;

    private PlanAllListResponseDto(List<PlanAllList> allLists) {
        this.planAllLists = allLists;
    }

    public static PlanAllListResponseDto of(List<PlanAllList> allLists) {
        return new PlanAllListResponseDto(allLists);
    }

    @Getter
    @NoArgsConstructor
    public static class PlanAllList{
        private long planListId;
        private String title;
        private String mainImage;

        public static PlanAllList of(PlanList planList) {
            PlanAllList planAllList = new PlanAllList();
            planAllList.planListId = planList.getPlanListId();
            planAllList.title = planList.getTitle();
            planAllList.mainImage = planList.getMainImage();
            return planAllList;
        }
    }
}
