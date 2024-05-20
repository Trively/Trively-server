package com.jida.dto.res.plan;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import com.jida.dto.res.post.PostListResponseDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class PlanAllListResponse extends BaseResponse {

    PlanAllListResponseDto data;

    private PlanAllListResponse(Boolean success, String message, PlanAllListResponseDto data) {
        super(success, message);
        this.data = data;
    }

    public static PlanAllListResponse of(Boolean success, String message, PlanAllListResponseDto data) {
        return new PlanAllListResponse(success, message, data);
    }

    public static ResponseEntity<PlanAllListResponse> newResponse(SuccessCode code, PlanAllListResponseDto data) {
        return ResponseEntity.status(code.getStatus())
                .body(PlanAllListResponse.of(true, code.getMessage(), data));
    }
}
