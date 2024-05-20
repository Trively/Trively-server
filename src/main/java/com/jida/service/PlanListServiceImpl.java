package com.jida.service;

import com.jida.domain.Member;
import com.jida.domain.PlanList;
import com.jida.dto.req.PlanListSaveRequestDto;
import com.jida.exception.CustomException;
import com.jida.mapper.MemberMapper;
import com.jida.mapper.PlanListMapper;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.jida.constants.ExceptionCode.PLAN_CANT_GET;
import static com.jida.constants.ExceptionCode.PLAN_LIST_NOT_FOUND;

@Slf4j
@Service
@RequiredArgsConstructor
public class PlanListServiceImpl implements PlanListService {

    private final PlanListMapper planListMapper;
    private final MemberMapper memberMapper;

    @Override
    public Long savePlanList(PlanListSaveRequestDto requestDto) {
        //TODO: 객체를 그대로 넘기는게 맞을까?, 필요한 값만 넘기는게 맞을까?
        PlanList planList = requestDto.ToEntity(requestDto);
        planListMapper.save(planList);
        return planList.getPlanListId();
    }

    @Override
    public PlanList findById(Long planListId) {
        return planListMapper.findById(planListId)
                .orElseThrow(() -> new CustomException(PLAN_LIST_NOT_FOUND));
    }

    @Override
    public void delete(Long planListId, Long memberId) {
        Member member = memberMapper.findById(memberId);
        PlanList planList = findById(planListId);

        if(member.getMemberId() != planList.getMember().getMemberId()) {
            throw new CustomException(PLAN_CANT_GET);
        }

        planListMapper.delete(planListId);
    }

    @Override
    public void update(Long planListId, String title) {
        Map<String, Object> map =new HashMap<>();
        map.put("planListId", planListId);
        map.put("title", title);
        planListMapper.update(map);
    }
}
