package com.jida.service;

import java.util.List;

import com.jida.domain.Post;
import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponseDto;

public interface PostService {
	List<Post> showList(long boardId) throws Exception;
	long writePost(PostSaveRequestDto postSaveRequestDto);
	PostDetailResponseDto viewPost(long postId);
	void modifyPost(Post postDto);
	void deletePost(long postId);
	
}
