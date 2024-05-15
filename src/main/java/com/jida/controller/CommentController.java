package com.jida.controller;

import com.jida.dto.req.CommentSaveRequestDto;
import com.jida.dto.res.comment.CommentDetailResponse;
import com.jida.dto.res.comment.CommentDetailResponseDto;
import com.jida.dto.res.comment.CommentListResponse;
import com.jida.dto.res.comment.CommentListResponseDto;
import com.jida.dto.res.comment.CommentResponse;
import com.jida.exception.CustomException;
import com.jida.service.CommentService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.jida.constants.SuccessCode.COMMENT_LIST_SUCCESS;
import static com.jida.constants.SuccessCode.COMMENT_SAVE_SUCCESS;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentResponse> saveComment(@RequestParam Long postId,
                                                       @RequestParam(required = false) Long parentId,
                                                       @Valid @RequestBody CommentSaveRequestDto commentSaveRequestDto) {
        commentService.save(postId, parentId, commentSaveRequestDto);
        return CommentResponse.newResponse(COMMENT_SAVE_SUCCESS);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<CommentListResponse> findAll(@PathVariable Long postId) {
        CommentListResponseDto responseDto = commentService.findAll(postId);
        return CommentListResponse.newResponse(COMMENT_LIST_SUCCESS, responseDto);
    }
}
