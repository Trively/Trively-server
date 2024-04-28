package com.jida.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

import com.jida.domain.Post;

@Mapper
public interface PostMapper {
	void writePost(Post post);
	Post findById(long postId);
	void updatePost(Post post);
	void deletePost(long PostId);
	List<Post> findPosts(String order, long boardId, int offset, int limit);
}
