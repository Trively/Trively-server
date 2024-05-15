package com.jida.dto.res.comment;

import com.jida.domain.Comment;
import com.jida.domain.Member;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentListResponseDto {
    private List<CommentList> list;

    private CommentListResponseDto(List<CommentList> comments) {
        this.list = comments;
    }

    public static CommentListResponseDto of(List<CommentList> comments) {
        return new CommentListResponseDto(comments);
    }

    @Getter
    @NoArgsConstructor
    public static class CommentList{
        private Long commentId;
        private Long parentId;
        private Long memberId;
        private String nickname;
        private String content;
        private boolean writer;
        private String createdAt;

        public static CommentList of(Comment comment, boolean writer) {
            CommentList commentList = new CommentList();
            commentList.commentId = comment.getCommentId();
            commentList.parentId = comment.getParentId();
            commentList.memberId = comment.getMember().getMemberId();
            commentList.nickname = comment.getMember().getNickname();
            commentList.content = comment.getContent();
            commentList.writer = writer;
            commentList.createdAt = comment.getCreatedAt().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm"));
            return commentList;
        }
    }
}
