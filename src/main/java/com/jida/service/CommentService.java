package com.jida.service;

import com.jida.dto.req.CommentSaveRequestDto;
import com.jida.dto.res.comment.CommentDetailResponseDto;
import com.jida.dto.res.comment.CommentListResponseDto;
import java.util.List;

public interface CommentService {
    void save(Long postId, Long parentId, CommentSaveRequestDto commentSaveRequestDto);

    CommentListResponseDto findAll(Long postId);
}
