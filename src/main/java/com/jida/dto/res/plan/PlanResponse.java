package com.jida.dto.res.plan;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import org.springframework.http.ResponseEntity;

public class PlanResponse extends BaseResponse {
    private PlanResponse(Boolean success, String message) {
        super(success, message);
    }

    public static PlanResponse of(Boolean success, String message) {
        return new PlanResponse(success, message);
    }

    public static ResponseEntity<PlanResponse> newResponse(SuccessCode code) {
        return ResponseEntity.status(code.getStatus()).body(PlanResponse.of(true, code.getMessage()));
    }
}
