package com.jida.mapper;

import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

import com.jida.domain.Post;

@Mapper
public interface PostMapper {
	//TODO: Optional 로 받기
	void writePost(Post post);
	Optional<Post> findById(long postId);
	void updateHit(long postId);
	void updatePost(Post post);
	void deletePost(long PostId);
	List<Post> findPosts(String order, long boardId, int offset, int limit);
	int countAllPosts(long boardId);

	void addCommentCnt(Long postId);

	void diffCommentCnt(Long postId);

	void addLikeCnt(Long postId);

	void diffLikeCnt(Long postId);
	List<Post> findByMember(long memberId, int offset, int limit);
	int countAllPostsByMember(long memberId);
}
