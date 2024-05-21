package com.jida.dto.res.plan;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class PlanMemberResponse extends BaseResponse {
    PlanMemberResponseDto data;

    private PlanMemberResponse(Boolean success, String message, PlanMemberResponseDto data) {
        super(success, message);
        this.data = data;
    }
    public static PlanMemberResponse of(Boolean success, String message, PlanMemberResponseDto data) {
        return new PlanMemberResponse(success, message, data);
    }

    public static ResponseEntity<PlanMemberResponse> newResponse(SuccessCode code, PlanMemberResponseDto data){
        PlanMemberResponse response = PlanMemberResponse.of(true, code.getMessage(), data);
        return new ResponseEntity<>(response, code.getStatus());
    }
}
