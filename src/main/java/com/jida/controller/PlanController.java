package com.jida.controller;

import com.jida.dto.req.PlanSaveRequestDto;
import com.jida.dto.res.plan.PlanListResponse;
import com.jida.dto.res.plan.PlanListResponseDto;
import com.jida.service.PlanService;
import com.jida.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jida.constants.SuccessCode.PLAN_SAVE_SUCCESS;

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
}
