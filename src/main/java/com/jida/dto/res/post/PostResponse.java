package com.jida.dto.res.post;

import com.jida.constants.SuccessCode;
import com.jida.dto.BaseResponse;
import org.springframework.http.ResponseEntity;

public class PostResponse extends BaseResponse {
    private PostResponse(Boolean success, String message){
        super(success, message);
    }

    public static PostResponse of(Boolean success, String message){
        return new PostResponse(success, message);
    }

    public static ResponseEntity<PostResponse> newResponse(SuccessCode code){
        return new ResponseEntity<>(PostResponse.of(true, code.getMessage()), code.getStatus());
    }
}
