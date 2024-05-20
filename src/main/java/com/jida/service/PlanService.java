package com.jida.service;

import com.jida.dto.req.PlanSaveRequestDto;

public interface PlanService {
    void savePlan(PlanSaveRequestDto planSaveRequestDto, long memberId);
}
