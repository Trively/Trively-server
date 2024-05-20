package com.jida.service;

import com.jida.dto.req.PlanSaveRequestDto;
import com.jida.dto.res.plan.PlanListResponseDto;

public interface PlanService {
    PlanListResponseDto savePlan(PlanSaveRequestDto planSaveRequestDto, long memberId);
    PlanListResponseDto showAll(long planListId, long memberId);
    void updatePlan(PlanSaveRequestDto planSaveRequestDto, long planListId, long memberId);
}
