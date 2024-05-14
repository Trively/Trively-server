package com.jida.service;

import com.jida.dto.req.CommentSaveRequestDto;
import com.jida.dto.res.comment.CommentDetailResponseDto;

public interface CommentService {
    CommentDetailResponseDto saveComment(Long postId, Long parentCommentId, CommentSaveRequestDto commentSaveRequestDto);
}
