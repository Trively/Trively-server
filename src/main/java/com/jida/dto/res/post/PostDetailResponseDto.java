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
		private String nickName;
		private long memberId;
		private int commentCnt;

		public PostDetail(long postId, String title, String content, String createdAt, long hit, long boardId,
						  String nickName, long memberId, int commentCnt) {
			this.postId = postId;
			this.title = title;
			this.content = content;
			this.createdAt = createdAt;
			this.hit = hit;
			this.boardId = boardId;
			this.nickName = nickName;
			this.memberId = memberId;
			this.commentCnt = commentCnt;
		}
		
		
	}
}
