package com.jida.service;

import com.jida.dto.res.post.PostListResponseDto;
import com.jida.dto.res.post.PostListResponseDto.PostList;
import java.util.List;

import com.jida.domain.Post;
import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponseDto;

public interface PostService {
	PostListResponseDto showList(String order, long boardId, int pageIndex, int pageSize);
	long writePost(PostSaveRequestDto postSaveRequestDto);
	void updateHit(long postId);
	PostDetailResponseDto viewPost(long postId);
	long modifyPost(long postId, PostSaveRequestDto postSaveRequestDto);
	void deletePost(long postId);
	boolean clickPostLike(Long postId);
	boolean clickPostScrap(Long postId);
	PostListResponseDto showScrap(String order, long boardId, int pageIndex, int pageSize);
}
