package com.jida.controller;

import com.jida.dto.req.PlanSaveRequestDto;
import com.jida.dto.res.plan.PlanListResponse;
import com.jida.dto.res.plan.PlanListResponseDto;
import com.jida.dto.res.plan.PlanResponse;
import com.jida.service.PlanService;
import com.jida.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{planListId}")
    public ResponseEntity<PlanResponse> updatePlan(@Valid @RequestBody PlanSaveRequestDto planSaveRequestDto,
                                                   @PathVariable long planListId, HttpServletRequest request) {
        long memberId = jwtUtil.getUserId(request.getHeader("Authorization"));
        planService.updatePlan(planSaveRequestDto, planListId, memberId);
        return PlanResponse.newResponse(PLAN_UPDATE_SUCCESS);
    }
}
