package com.jida.service;

import static com.jida.constants.ExceptionCode.POST_NOT_FOUND;

import com.jida.domain.Board;
import com.jida.domain.Member;
import com.jida.domain.PostLike;
import com.jida.domain.PostScrap;
import com.jida.dto.res.board.BoardListResponseDto.BoardList;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.dto.res.post.PostListResponseDto.PostList;
import com.jida.exception.CustomException;
import com.jida.mapper.PostLikeMapper;
import com.jida.mapper.PostScrapMapper;
import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
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
	private final PostLikeMapper postLikeMapper;
	private final PostScrapMapper postScrapMapper;
	private final MemberService memberService;

	//TODO: 예외 처리 및 Optional 처리
	@Override
	public PostListResponseDto showList(String order, long boardId, int pageIndex, int pageSize) {
		List<PostList> posts = postMapper.findPosts(order, boardId, (pageIndex - 1) * pageSize, pageSize).stream()
				.map(PostList::of)
				.collect(Collectors.toList());
		int totalCount = postMapper.countAllPosts(boardId);

		return PostListResponseDto.of(pageIndex, pageSize, totalCount, posts);
	}

	@Override
	public long writePost(String id, PostSaveRequestDto postSaveRequestDto) {
		Board board = boardMapper.findById(postSaveRequestDto.getBoardName());
		Member member = Member.detailToMember(memberService.findById(id));
		Post post = Post.creatPost(member, board, postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
		postMapper.writePost(post);
		
		return post.getPostId();	
	}

	@Override
	public void updateHit(long postId) {
		postMapper.updateHit(postId);
	}

	@Override
	public PostDetailResponseDto viewPost(long postId) {
		Post post = postMapper.findById(postId)
				.orElseThrow(() -> new CustomException(POST_NOT_FOUND));
		PostDetail postDetail = new PostDetail(post.getPostId(), post.getTitle(), post.getContent(), post.getCreatedAt(), post.getHit(),
				post.getBoard().getBoardId(), post.getMember().getNickname(), post.getMember().getMemberId(), post.getCommentCnt(), post.getLikeCnt());

		return PostDetailResponseDto.of(postDetail);
	}

	@Override
	public long modifyPost(long postId, PostSaveRequestDto postSaveRequestDto) {
		Member member = getMember();
		Board board = boardMapper.findById(postSaveRequestDto.getBoardName());

		Post post = Post.creatPost(member, board, postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
		postMapper.updatePost(post);
		
		return post.getPostId();
	}

	@Override
	public void deletePost(long postId) {
		postMapper.deletePost(postId);
	}

	@Override
	public boolean clickPostLike(Long postId) {
		Member member = getMember();
		Optional<PostLike> existPostLike = postLikeMapper.findByUserAndPost(member.getMemberId(), postId);

		if (existPostLike.isPresent()) {
			postLikeMapper.delete(existPostLike.get());
			postMapper.diffLikeCnt(postId);
			return false;
		}
		PostLike postLike = PostLike.createPostLike(member.getMemberId(), postId);
		postLikeMapper.save(postLike);
		postMapper.addLikeCnt(postId);
		return true;
	}

	@Override
	public boolean clickPostScrap(Long postId) {
		Member member = getMember();
		Post post = postMapper.findById(postId)
				.orElseThrow(() -> new CustomException(POST_NOT_FOUND));

		Optional<PostScrap> existScrap = postScrapMapper.findByUserAndPost(member.getMemberId(), postId);
		if (existScrap.isPresent()) {
			postScrapMapper.delete(existScrap.get());
			return false;
		}
		PostScrap postScrap = PostScrap.createPostScrap(member, post);
		postScrapMapper.save(postScrap);
		return true;
	}

	@Override
	public PostListResponseDto showScrap(String order, long boardId, int pageIndex, int pageSize) {
		//TODO: 멤버를 받아서 넘겨줘야 함
		Member member = getMember();

		List<PostList> posts = postScrapMapper.findScraps(order, boardId, member.getMemberId(), (pageIndex - 1) * pageSize, pageSize).stream()
				.map(postScrap -> PostList.of(postScrap.getPost()))
				.collect(Collectors.toList());
		int totalCount = postScrapMapper.countAllPosts(boardId, member.getMemberId());

		return PostListResponseDto.of(pageIndex, pageSize, totalCount, posts);
	}


	//TODO: jwt 토큰 구현 시 변경 필요
	private Member getMember() {
		Member member = new Member();
		member.setMemberId(1L);
		member.setEmail("ssafy@ssafy.com");
		member.setId("ssafy");
		member.setPassword("1234");
		member.setNickname("김싸피");
		return member;
	}
}
