package com.jida.dto.res.comment;

import com.jida.domain.Comment;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentDetailResponseDto {
    private Long commentId;
    private Long parentId;
    private String content;
    private LocalDateTime createdAt;

    private CommentDetailResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.parentId = comment.getParentId();
        this.content = comment.getContent();
        this.createdAt = comment.getCreatedAt();
    }

    public static CommentDetailResponseDto of(Comment comment) {
        return new CommentDetailResponseDto(comment);
    }

}
