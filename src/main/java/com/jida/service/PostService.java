package com.jida.service;

import com.jida.domain.Member;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.dto.res.post.PostListResponseDto.PostList;
import java.util.List;

import com.jida.domain.Post;
import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponseDto;

public interface PostService {
	PostListResponseDto showList(String order, long boardId, int pageIndex, int pageSize);
	long writePost(long memberId, PostSaveRequestDto postSaveRequestDto);
	void updateHit(long postId);
	PostDetailResponseDto viewPost(long postId);
	long modifyPost(long memberId,long postId, PostSaveRequestDto postSaveRequestDto);
	void deletePost(long memberId,long postId);
	boolean clickPostLike(long memberId,Long postId);
	boolean clickPostScrap(long memberId,Long postId);
	PostListResponseDto showScrap(long memberId,String order, long boardId, int pageIndex, int pageSize);
}
