package com.jida.dto.res.comment;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class CommentResponse extends BaseResponse {
    private CommentResponse(Boolean success, String message) {
        super(success, message);
    }

    public static CommentResponse of(Boolean success, String message) {
        return new CommentResponse(success, message);
    }

    public static ResponseEntity<CommentResponse> newResponse(SuccessCode code) {
        return new ResponseEntity<>(CommentResponse.of(true, code.getMessage()), code.getStatus());
    }
}
