package com.jida.service;

import com.jida.domain.Member;
import com.jida.domain.PlanList;

import com.jida.dto.req.PlanListSaveRequestDto;
import com.jida.dto.res.plan.PlanAllListResponseDto;
import java.util.Optional;

public interface PlanListService {
    Long savePlanList(PlanListSaveRequestDto requestDto);
    PlanList findById(Long planListId);
    void delete(Long planListId, Long memberId);
    void update(Long planListId, String title);
    PlanAllListResponseDto findAllByMemberId(Long memberId);
}
