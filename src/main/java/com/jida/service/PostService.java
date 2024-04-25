package com.jida.service;

import java.util.List;

import com.jida.domain.Post;

public interface PostService {
	List<Post> showList(long boardId) throws Exception;
	void writePost(Post postDto);
	Post viewPost(long postId);
	void modifyPost(Post postDto);
	void deletePost(long postId);
	
}
