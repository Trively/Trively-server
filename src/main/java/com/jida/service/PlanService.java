package com.jida.service;

import com.jida.dto.req.PlanSaveRequestDto;
import com.jida.dto.req.PlanUpdateRequestDto;
import com.jida.dto.res.plan.PlanListResponseDto;

public interface PlanService {
    PlanListResponseDto savePlan(PlanSaveRequestDto planSaveRequestDto, long memberId);
    PlanListResponseDto showAll(long planListId, long memberId);
    void updatePlan(PlanUpdateRequestDto planSaveRequestDto, long memberId);
    boolean updateOpen(long planId, long memberId);
}
