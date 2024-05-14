package com.jida.dto.res.comment;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class CommentDetailResponse extends BaseResponse {
    private CommentDetailResponseDto data;

    private CommentDetailResponse(Boolean success, String message, CommentDetailResponseDto data) {
        super(success, message);
        this.data = data;
    }

    public static CommentDetailResponse of(Boolean success, String message, CommentDetailResponseDto data) {
        return new CommentDetailResponse(success, message, data);
    }

    public static ResponseEntity<CommentDetailResponse> newResponse(SuccessCode code, CommentDetailResponseDto data) {
        CommentDetailResponse response = CommentDetailResponse.of(true, code.getMessage(), data);
        return new ResponseEntity<>(response, code.getStatus());
    }
}
