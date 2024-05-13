package com.jida.domain;

import lombok.Getter;

@Getter
public class PostLike {
    private Long memberId;
    private Long postId;

    public static PostLike createPostLike(Long memberId, Long postId) {
        PostLike postLike = new PostLike();
        postLike.memberId = memberId;
        postLike.postId = postId;
        return postLike;
    }
}
