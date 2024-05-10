package com.jida.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
	private long postId;
	private String title;
	private String content;
	private String createdAt;
	private long hit;
	private long boardId;
	private long memberId;
	private Member member;
	private Board board;

	public static Post creatPost(long memberId, long boardId, String title, String content) {
		Post post = new Post();
		post.memberId = memberId;
		post.boardId = boardId;
		post.title = title;
		post.content = content;
		post.hit = 0;
		return post;
	}
}
