package com.jida.service;

import com.jida.domain.Member;
import com.jida.domain.PlanList;
import com.jida.exception.CustomException;
import com.jida.mapper.PlanListMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.jida.constants.ExceptionCode.PLAN_LIST_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanListServiceImpl implements PlanListService {

    private final PlanListMapper planListMapper;

    @Override
    public Long savePlanList(Member member) {
        //TODO: 객체를 그대로 넘기는게 맞을까?, 필요한 값만 넘기는게 맞을까?
        PlanList planList = PlanList.createPlanList(member);
        planListMapper.save(planList);
        return planList.getPlanListId();
    }

    @Override
    public PlanList findById(Long planListId) {
        return planListMapper.findById(planListId)
                .orElseThrow(() -> new CustomException(PLAN_LIST_NOT_FOUND));
    }
}
