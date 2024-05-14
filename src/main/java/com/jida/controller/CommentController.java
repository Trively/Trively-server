package com.jida.controller;

import com.jida.dto.req.CommentSaveRequestDto;
import com.jida.dto.res.comment.CommentDetailResponse;
import com.jida.dto.res.comment.CommentDetailResponseDto;
import com.jida.dto.res.comment.CommentResponse;
import com.jida.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.jida.constants.SuccessCode.COMMENT_SAVE_SUCCESS;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{postId}/{parentCommentId}")
    public ResponseEntity<CommentDetailResponse> saveComment(@PathVariable Long postId, @PathVariable(required = false) Long parentCommentId,
                                                             @Valid @ModelAttribute CommentSaveRequestDto commentSaveRequestDto) {
        CommentDetailResponseDto responseDto = commentService.saveComment(postId, parentCommentId, commentSaveRequestDto);
        return CommentDetailResponse.newResponse(COMMENT_SAVE_SUCCESS, responseDto);
    }
}
