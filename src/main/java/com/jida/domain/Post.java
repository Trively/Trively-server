package com.jida.domain;

public class Post {
	private long postId;
	private String title;
	private String content;
	private String createdAt;
	private long hit;
	private long boardId;
	private long memberId;
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public long getHit() {
		return hit;
	}
	public void setHit(long hit) {
		this.hit = hit;
	}
	public long getBoardId() {
		return boardId;
	}
	public void setBoardId(long boardId) {
		this.boardId = boardId;
	}
	public long getMemberId() {
		return memberId;
	}
	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}
	
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
