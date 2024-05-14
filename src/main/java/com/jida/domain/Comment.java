package com.jida.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Comment {
    private Long commentId;
    private Post post;
    private Member member;
    private Long parentCommentId;
    private String content;
    private LocalDateTime createTime;
}
