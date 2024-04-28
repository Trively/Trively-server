package com.jida.service;

import com.jida.dto.res.board.BoardListResponseDto.BoardList;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.dto.res.post.PostListResponseDto.PostList;
import java.util.List;

import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import com.jida.domain.Post;
import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponseDto;
import com.jida.dto.res.post.PostDetailResponseDto.PostDetail;
import com.jida.mapper.BoardMapper;
import com.jida.mapper.PostMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
	
	private final PostMapper postMapper;
	private final BoardMapper boardMapper;

	@Override
	public PostListResponseDto showList(String order, long boardId, int pageIndex, int pageSize) {
		List<PostList> posts = postMapper.findPosts(order, boardId, (pageIndex - 1) * pageSize, pageSize).stream()
				.map(PostList::of)
				.collect(Collectors.toList());
		int totalCount = posts.size();

		return PostListResponseDto.of(pageIndex, pageSize, totalCount, posts);
	}

	@Override
	public long writePost(PostSaveRequestDto postSaveRequestDto) {
		//임시로 지정
		//jwt 토큰 사용 시 변경 필요!!!!!
		long userId = 1L;
		long boardId = boardMapper.findId(postSaveRequestDto.getBoardName());
		Post post = Post.creatPost(userId, boardId, postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
		postMapper.writePost(post);
		
		return post.getPostId();	
	}

	@Override
	public PostDetailResponseDto viewPost(long postId) {
		Post post = postMapper.findById(postId);
		PostDetail postDetail = new PostDetail(post.getPostId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getHit(), post.getBoardId(), post.getMemberId());

		return PostDetailResponseDto.of(postDetail);
	}

	@Override
	public long modifyPost(long postId, PostSaveRequestDto postSaveRequestDto) {
		//사용자 일치 확인
		long userId = 1L;
		long boardId = boardMapper.findId(postSaveRequestDto.getBoardName());
		Post post = Post.creatPost(userId, boardId, postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
		post.setPostId(postId);
		postMapper.updatePost(post);
		
		return post.getPostId();
	}

	@Override
	public void deletePost(long postId) {
		postMapper.deletePost(postId);
	}
	
}
