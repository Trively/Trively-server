package com.jida.service;

import com.jida.dto.req.PlanSaveRequestDto;
import com.jida.dto.req.PlanUpdateRequestDto;
import com.jida.dto.res.plan.PlanListResponseDto;
import com.jida.dto.res.plan.PlanMemberResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface PlanService {
    PlanListResponseDto savePlan(PlanSaveRequestDto planSaveRequestDto, long memberId);
    PlanListResponseDto showAll(long planListId, long memberId);
    void updatePlan(PlanUpdateRequestDto planSaveRequestDto, long memberId);
    PlanMemberResponseDto findMessageMembers(long memberId, long attractionId, LocalDate date);
}
