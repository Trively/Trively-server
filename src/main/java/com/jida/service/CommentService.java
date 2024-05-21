package com.jida.service;

import com.jida.dto.req.CommentSaveRequestDto;
import com.jida.dto.res.comment.CommentDetailResponseDto;
import com.jida.dto.res.comment.CommentListResponseDto;
import java.util.List;

public interface CommentService {
    void save(long memberId, CommentSaveRequestDto commentSaveRequestDto);

    CommentListResponseDto findAll(long memberId,Long postId);

    void delete(long memberId, Long postId, Long commentId);
    CommentListResponseDto findByMember(long memberId);
}
