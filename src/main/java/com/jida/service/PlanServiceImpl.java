package com.jida.service;

import static com.jida.constants.ExceptionCode.PLAN_CANT_GET;
import static com.jida.constants.ExceptionCode.PLAN_NOT_FOUND;
import static com.jida.dto.res.plan.PlanListResponseDto.PlanLists;

import com.jida.domain.Attraction;
import com.jida.domain.Member;
import com.jida.domain.Plan;
import com.jida.domain.PlanList;
import com.jida.dto.req.PlanListSaveRequestDto;
import com.jida.dto.req.PlanSaveRequestDto;
import com.jida.dto.req.PlanUpdateRequestDto;
import com.jida.dto.res.plan.PlanListResponseDto;
import com.jida.dto.res.plan.PlanMemberResponseDto;
import com.jida.exception.CustomException;
import com.jida.mapper.MemberMapper;
import com.jida.mapper.PlanMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

    //TODO: MemberService 에는 findById가 없으므로 일단 사용. 추후 변경 요망.
    private final AttractionService attractionService;
    private final PlanListService planListService;
    private final MemberMapper memberMapper;
    private final PlanMapper planMapper;

    @Override
    public PlanListResponseDto savePlan(PlanSaveRequestDto requestDto, long memberId) {
        Member member = getMember(memberId);
        Long planListId = planListService.savePlanList(PlanListSaveRequestDto.of(member, requestDto.getTitle()));
        PlanList planList = planListService.findById(planListId);

        //데이터 변환
        List<Plan> plans = requestDto.getPlans().stream()
                .map(planDto -> {
                    Attraction attraction = attractionService.findById(planDto.getAttractionId());
                    return requestDto.ToEntity(planList, attraction, planDto.getOrders(), planDto.getPlanDate(), planDto.isOpen());
                })
                .toList();

        //저장
        planMapper.save(plans);
        attractionService.addPlanCnt(plans.stream()
                .map(planDto -> planDto.getAttraction().getAttractionId())
                .toList());

        //반환값
        List<PlanLists> list = plans.stream()
                .map(PlanLists::of)
                .toList();
        return PlanListResponseDto.of(requestDto.getTitle(), list);
    }

    @Override
    public PlanListResponseDto showAll(long planListId, long memberId) {
        Member member = getMember(memberId);
        PlanList planList = planListService.findById(planListId);

        if (member.getMemberId() != planList.getMember().getMemberId()) {
            throw new CustomException(PLAN_CANT_GET);
        }

        List<PlanLists> list = planMapper.selectAll(planListId).stream()
                .map(PlanLists::of)
                .toList();
        return PlanListResponseDto.of(planList.getTitle(), list);
    }

    @Override
    public void updatePlan(PlanUpdateRequestDto requestDto, long memberId) {
        Member member = getMember(memberId);
        PlanList planList = planListService.findById(requestDto.getPlanListId());

        if (member.getMemberId() != planList.getMember().getMemberId()) {
            throw new CustomException(PLAN_CANT_GET);
        }

        List<Plan> plans = requestDto.getPlans().stream()
                .map(planDto -> {
                    Attraction attraction = attractionService.findById(planDto.getAttractionId());
                    return requestDto.ToEntity(planList, attraction, planDto.getOrders(), planDto.getPlanDate(), planDto.isOpen());
                })
                .toList();

        //수정 시, plan 하나의 값만 변경되는 것이 아니라 모든 plan 이 변경될 수 있다.
        //따라서 수정시에 모든 기존 데이터 삭제 후 다시 저장
        planListService.update(planList.getPlanListId(), requestDto.getTitle());
        planMapper.deleteAllPlan(planList.getPlanListId());
        planMapper.save(plans);
    }

    @Override
    public PlanMemberResponseDto findMessageMembers(long memberId, long attractionId, LocalDate date) {
        List<PlanMemberResponseDto.MessageMember> list = memberMapper.findMessageMembers(memberId,attractionId,date).stream().map(PlanMemberResponseDto.MessageMember::of).collect(Collectors.toList());
        return PlanMemberResponseDto.of(list);
    }
    @Override
    public boolean updateOpen(long planId, long memberId) {
        Member member = getMember(memberId);
        Plan plan = planMapper.findById(planId)
                .orElseThrow(() -> new CustomException(PLAN_NOT_FOUND));
        //TODO: Optional 적용
        PlanList planList = planListService.findById(plan.getPlanList().getPlanListId());

        if (planList.getMember().getMemberId() != member.getMemberId()) {
            throw new CustomException(PLAN_CANT_GET);
        }

        boolean result = true;
        if (plan.isOpen()) {
            result = false;
        }
        planMapper.clickOpen(planId);
        return result;
    }

    private Member getMember(long memberId) {
        return memberMapper.findById(memberId);
    }
}
