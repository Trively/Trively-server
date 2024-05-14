package com.jida.service;

import com.jida.dto.req.CommentSaveRequestDto;
import com.jida.dto.res.comment.CommentDetailResponseDto;
import com.jida.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;


    @Override
    public CommentDetailResponseDto saveComment(Long postId, Long parentCommentId, CommentSaveRequestDto commentSaveRequestDto) {
        //댓글
        if(parentCommentId == null) {

        }

        //대댓글
        return null;
    }
}
