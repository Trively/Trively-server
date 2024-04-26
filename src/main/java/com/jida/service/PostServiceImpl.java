package com.jida.service;

import java.util.List;

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
	public List<Post> showList(long boardId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long writePost(PostSaveRequestDto postSaveRequestDto) {
		//임시로 지정
		//jwt 토큰 사용 시 변경 필요!!!!!
		long userId = 1L;
		long boardId = boardMapper.findId(postSaveRequestDto.getBoardName());
		Post post = Post.creatPost(userId, boardId, postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
		postMapper.writePost(post);
		
		return  post.getPostId();	
	}

	@Override
	public PostDetailResponseDto viewPost(long postId) {
		Post post = postMapper.findById(postId);
		PostDetail postDetial = new PostDetail(post.getPostId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getHit(), post.getBoardId(), post.getMemberId());

		return PostDetailResponseDto.of(postDetial);
	}

	@Override
	public void modifyPost(Post postDto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePost(long postId) {
		// TODO Auto-generated method stub
		
	}
	
}
