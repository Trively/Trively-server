package com.jida.controller;

import static com.jida.constants.SuccessCode.PLAN_ALL_LIST_SUCCESS;
import static com.jida.constants.SuccessCode.PLAN_DELETE_SUCCESS;

import com.jida.dto.res.plan.PlanAllListResponse;
import com.jida.dto.res.plan.PlanAllListResponseDto;
import com.jida.dto.res.plan.PlanResponse;
import com.jida.service.PlanListService;
import com.jida.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/planList")
public class PlanListController {

    private final PlanListService planListService;
    private final JWTUtil jwtUtil;

    @DeleteMapping("/{planListId}")
    public ResponseEntity<PlanResponse> deletePlanList(@PathVariable Long planListId, HttpServletRequest request) {
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        planListService.delete(planListId, memberId);
        return PlanResponse.newResponse(PLAN_DELETE_SUCCESS);
    }

    @GetMapping
    public ResponseEntity<PlanAllListResponse> showAll(HttpServletRequest request) {
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        PlanAllListResponseDto response = planListService.findAllByMemberId(memberId);
        return PlanAllListResponse.newResponse(PLAN_ALL_LIST_SUCCESS, response);
    }
}
