package com.jida.service;

import com.jida.dto.req.CommentSaveRequestDto;
import com.jida.dto.res.comment.CommentDetailResponseDto;

public interface CommentService {
    void save(Long postId, Long parentId, CommentSaveRequestDto commentSaveRequestDto);
}
