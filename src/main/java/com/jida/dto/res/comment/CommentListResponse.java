package com.jida.dto.res.comment;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@Getter
@NoArgsConstructor
public class CommentListResponse extends BaseResponse {
    private CommentListResponseDto data;

    private CommentListResponse(Boolean success, String message, CommentListResponseDto data) {
        super(success, message);
        this.data = data;
    }

    private static CommentListResponse of(Boolean success, String message, CommentListResponseDto data) {
        return new CommentListResponse(success, message, data);
    }

    public static ResponseEntity<CommentListResponse> newResponse(SuccessCode code, CommentListResponseDto data) {
        CommentListResponse response = CommentListResponse.of(true, code.getMessage(), data);
        return new ResponseEntity<CommentListResponse>(response, code.getStatus());
    }
}
