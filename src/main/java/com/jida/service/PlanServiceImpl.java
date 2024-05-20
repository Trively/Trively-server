package com.jida.service;

import com.jida.dto.req.PlanSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

    @Override
    public void savePlan(PlanSaveRequestDto planSaveRequestDto, long memberId) {

    }
}
