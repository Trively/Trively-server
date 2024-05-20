package com.jida.service;

import com.jida.domain.Attraction;
import com.jida.domain.Member;
import com.jida.domain.Plan;
import com.jida.domain.PlanList;
import com.jida.dto.req.PlanSaveRequestDto;
import com.jida.dto.res.plan.PlanListResponseDto;
import com.jida.exception.CustomException;
import com.jida.mapper.MemberMapper;
import com.jida.mapper.PlanMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;

import static com.jida.constants.ExceptionCode.PLAN_CANT_GET;
import static com.jida.dto.res.plan.PlanListResponseDto.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService{

    //TODO: MemberService 에는 findById가 없으므로 일단 사용. 추후 변경 요망.
    private final AttractionService attractionService;
    private final PlanListService planListService;
    private final MemberMapper memberMapper;
    private final PlanMapper planMapper;

    @Override
    public PlanListResponseDto savePlan(PlanSaveRequestDto requestDto, long memberId) {
        Member member = getMember(memberId);
        Long planListId = planListService.savePlanList(member);
        PlanList planList = planListService.findById(planListId);

        //데이터 변환
        List<Plan> plans = requestDto.getPlans().stream()
                .map(planDto -> {
                    Attraction attraction = attractionService.findById(planDto.getAttractionId());
                    return Plan.createPlan(planList, attraction, planDto.getOrders(), planDto.getPlanDate());
                })
                .toList();

        //저장
        planMapper.save(plans);

        //반환값
        List<PlanLists> list = plans.stream()
                .map(PlanLists::of)
                .toList();
        return PlanListResponseDto.of(list);
    }

    @Override
    public PlanListResponseDto showAll(long planListId, long memberId) {
        Member member = getMember(memberId);
        PlanList planList = planListService.findById(planListId);

        if(member.getMemberId() != planList.getMember().getMemberId()) {
            throw new CustomException(PLAN_CANT_GET);
        }

        List<PlanLists> list = planMapper.selectAll(planListId, memberId).stream()
                .map(PlanLists::of)
                .toList();
        return PlanListResponseDto.of(list);
    }

    private Member getMember(long memberId) {
        return memberMapper.findById(memberId);
    }
}
