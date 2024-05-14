package com.jida.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Comment {
    private Long commentId;
    private Post post;
    private Member member;
    private Long parentId;
    private String content;
    private LocalDateTime createdAt;

    public static Comment createComment(Post post, Member member, Long parentId, String content) {
        Comment comment = new Comment();
        comment.post = post;
        comment.member = member;
        comment.parentId = parentId;
        comment.content = content;
        return comment;
    }
}
