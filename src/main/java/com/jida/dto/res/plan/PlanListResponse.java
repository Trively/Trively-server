package com.jida.dto.res.plan;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import com.jida.dto.res.post.PostListResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class PlanListResponse extends BaseResponse {
    PlanListResponseDto data;

    private PlanListResponse(Boolean success, String message, PlanListResponseDto data) {
        super(success, message);
        this.data = data;
    }

    public static PlanListResponse of(Boolean success, String message, PlanListResponseDto data) {
        return new PlanListResponse(success, message, data);
    }

    public static ResponseEntity<PlanListResponse> newResponse(SuccessCode successCode, PlanListResponseDto data) {
        return ResponseEntity.status(successCode.getStatus())
                .body(PlanListResponse.of(true, successCode.getMessage(), data));
    }
}
