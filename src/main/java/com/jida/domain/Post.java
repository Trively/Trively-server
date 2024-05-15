package com.jida.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
public class Post {
	private long postId;
	private String title;
	private String content;
	private String createdAt;
	private long hit;
	private Member member;
	private Board board;
	private int commentCnt;
	private int likeCnt;

	public static Post creatPost(Member member, Board board, String title, String content) {
		Post post = new Post();
		post.member = member;
		post.board = board;
		post.title = title;
		post.content = content;
		post.hit = 0;

		return post;
	}
}
