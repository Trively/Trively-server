package com.jida.domain;

import static java.time.LocalTime.now;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
public class PostScrap {
    private Member member;
    private Post post;
    private String createdAt;

    public static PostScrap createPostScrap(Member member, Post post) {
        PostScrap postScrap = new PostScrap();
        postScrap.member = member;
        postScrap.post = post;
        postScrap.createdAt = now().toString();
        return postScrap;
    }
}
