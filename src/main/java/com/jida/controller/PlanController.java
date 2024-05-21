package com.jida.controller;

import com.jida.dto.req.PlanSaveRequestDto;
import com.jida.dto.req.PlanUpdateRequestDto;
import com.jida.dto.res.plan.*;
import com.jida.service.PlanService;
import com.jida.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

import static com.jida.constants.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {

    private final JWTUtil jwtUtil;
    private final PlanService planService;

    @PostMapping
    public ResponseEntity<PlanListResponse> savePlan(@Valid @RequestBody PlanSaveRequestDto planSaveRequestDto, HttpServletRequest request) {
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        PlanListResponseDto response = planService.savePlan(planSaveRequestDto, memberId);

        return PlanListResponse.newResponse(PLAN_SAVE_SUCCESS, response);
    }

    @GetMapping("/{planListId}")
    public ResponseEntity<PlanListResponse> showAll(@PathVariable long planListId, HttpServletRequest request) {
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        PlanListResponseDto response = planService.showAll(planListId, memberId);

        return  PlanListResponse.newResponse(PLAN_SHOW_SUCCESS, response);
    }

    @PutMapping
    public ResponseEntity<PlanResponse> updatePlan(@Valid @RequestBody PlanUpdateRequestDto planUpdateRequestDto, HttpServletRequest request) {
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        planService.updatePlan(planUpdateRequestDto, memberId);
        return PlanResponse.newResponse(PLAN_UPDATE_SUCCESS);
    }

    @GetMapping("/{attractionId}/{date}")
    public ResponseEntity<PlanMemberResponse> showMember(@PathVariable long attractionId, @PathVariable LocalDate date, HttpServletRequest request){
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        PlanMemberResponseDto response = planService.findMessageMembers(memberId, attractionId,date);
        return PlanMemberResponse.newResponse(PLAN_MEMBER_LIST_SUCCESS,response);
    }
}
