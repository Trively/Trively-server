package com.jida.dto.res.attraction;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class AttractionListResponse extends BaseResponse {
    AttractionListResponseDto data;

    private AttractionListResponse(Boolean success, String message, AttractionListResponseDto data){
        super(success, message);
        this.data = data;
    }
    public static AttractionListResponse of(Boolean success, String message, AttractionListResponseDto data){
        return new AttractionListResponse(success, message, data);
    }

    public static ResponseEntity<AttractionListResponse> newResponse(SuccessCode code, AttractionListResponseDto data){
        AttractionListResponse response = AttractionListResponse.of(true, code.getMessage(), data);
        return new ResponseEntity<>(response, code.getStatus());
    }
}
