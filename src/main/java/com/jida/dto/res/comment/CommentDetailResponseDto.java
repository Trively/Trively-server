package com.jida.dto.res.comment;

import com.jida.domain.Comment;
import com.jida.domain.Member;
import com.jida.domain.Post;

import java.time.LocalDateTime;

public class CommentDetailResponseDto {
    private Long commentId;
    private Long parentCommentId;
    private String content;
    private LocalDateTime createTime;

    private CommentDetailResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.parentCommentId = comment.getParentCommentId();
        this.content = comment.getContent();
        this.createTime = comment.getCreateTime();
    }

    public static CommentDetailResponseDto of(Comment comment) {
        return new CommentDetailResponseDto(comment);
    }

}
