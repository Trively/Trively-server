package com.jida.dto.res.post;

import com.jida.common.PageInfo;
import com.jida.domain.Post;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class PostListResponseDto extends PageInfo {
    private List<PostList> posts;

    private PostListResponseDto(int pageIndex, int pageSize, int totalCount, List<PostList> posts) {
        super(pageIndex, pageSize, totalCount);
        this.posts = posts;
    }

    public static PostListResponseDto of(int pageIndex, int pageSize, int totalCount, List<PostList> posts) {
        return new PostListResponseDto(pageIndex, pageSize, totalCount,posts);
    }

    @Getter
    @NoArgsConstructor
    public static class PostList {
        private long postId;
        private String title;
        private String content;
        private String createdAt;
        private long hit;
        private long boardId;
        private long memberId;

        public static PostList of(Post post) {
            PostList postList = new PostList();
            postList.postId = post.getPostId();
            postList.title = post.getTitle();
            postList.content = post.getContent();
            postList.createdAt = post.getCreatedAt();
            postList.hit = post.getHit();
            postList.boardId = post.getBoardId();
            postList.memberId = post.getMemberId();
            return postList;
        }
    }
}