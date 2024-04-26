package com.jida.dto.res.post;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostDetailResponseDto {
	
	private PostDetail post;
	
	private PostDetailResponseDto(PostDetail post) {
		this.post = post;
	}
	
	public static PostDetailResponseDto of(PostDetail post) {
		return new PostDetailResponseDto(post);
	}
	
	@Getter
	@NoArgsConstructor
	public static class PostDetail {
		private long postId;
		private String title;
		private String content;
		private String createdAt;
		private long hit;
		private long boardId;
		private long memberId;
		
		public PostDetail(long postId, String title, String content, String createdAt, long hit, long boardId,
				long memberId) {
			this.postId = postId;
			this.title = title;
			this.content = content;
			this.createdAt = createdAt;
			this.hit = hit;
			this.boardId = boardId;
			this.memberId = memberId;
		}
		
		
	}
}
