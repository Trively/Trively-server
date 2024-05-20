package com.jida.service;

import com.jida.domain.Member;
import com.jida.domain.PlanList;

import java.util.Optional;

public interface PlanListService {
    Long savePlanList(Member member);
    PlanList findById(Long planListId);
}
