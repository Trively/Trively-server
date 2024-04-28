package com.jida.service;

import com.jida.dto.res.post.PostListResponseDto;
import java.util.List;

import com.jida.domain.Post;
import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponseDto;

public interface PostService {
	PostListResponseDto showList(String order, long boardId, int pageIndex, int pageSize);
	long writePost(PostSaveRequestDto postSaveRequestDto);
	PostDetailResponseDto viewPost(long postId);
	long modifyPost(long postId, PostSaveRequestDto postSaveRequestDto);
	void deletePost(long postId);
	
}
