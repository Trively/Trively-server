package com.jida.service;

import com.jida.domain.Board;
import com.jida.domain.Member;
import com.jida.domain.PostLike;
import com.jida.domain.PostScrap;
import com.jida.dto.res.board.BoardListResponseDto.BoardList;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.dto.res.post.PostListResponseDto.PostList;
import com.jida.exception.CustomException;
import com.jida.mapper.*;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.jida.domain.Post;
import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponseDto;
import com.jida.dto.res.post.PostDetailResponseDto.PostDetail;

import lombok.RequiredArgsConstructor;

import static com.jida.constants.ExceptionCode.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImpl implements PostService {

	//TODO: 다른 Mapper 를 바로 참조하는게 맞는가? 서비스를 거쳐야하지 않을까? 고민하기
	private final PostMapper postMapper;
	private final BoardMapper boardMapper;
	private final MemberMapper memberMapper;
	private final PostLikeMapper postLikeMapper;
	private final PostScrapMapper postScrapMapper;

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
	public long writePost(long memberId, PostSaveRequestDto postSaveRequestDto) {
		Board board = boardMapper.findById(postSaveRequestDto.getBoardName());
		Member member = memberMapper.findById(memberId);
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
	public long modifyPost(long memberId, long postId, PostSaveRequestDto postSaveRequestDto) {
		Member member = getMember(memberId);
		Board board = boardMapper.findById(postSaveRequestDto.getBoardName());
		Optional<Post> origin = postMapper.findById(postId);
		Post post = Post.creatPost(member, board, postSaveRequestDto.getTitle(), postSaveRequestDto.getContent());
		if(origin.isEmpty()){
			throw new CustomException(POST_NOT_FOUND);
		}
		post.setPostId(postId);
		postMapper.updatePost(post);
		return post.getPostId();
	}

	@Override
	public void deletePost(long memberId,long postId) {
		Member member = getMember(memberId);
		Post post = postMapper.findById(postId)
				.orElseThrow(() -> new CustomException(POST_NOT_FOUND));
		if (post.getMember().getMemberId() != member.getMemberId()) {
			throw new CustomException(POST_CANT_DELETE);
		}
		postMapper.deletePost(postId);
	}

	@Override
	public boolean clickPostLike(long memberId,Long postId) {
		Member member = getMember(memberId);
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
	public boolean clickPostScrap(long memberId,Long postId) {
		Member member = getMember(memberId);
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
	public PostListResponseDto showScrap(long memberId,String order, long boardId, int pageIndex, int pageSize) {
		Member member = getMember(memberId);

		List<PostList> posts = postScrapMapper.findScraps(order, boardId, member.getMemberId(), (pageIndex - 1) * pageSize, pageSize).stream()
				.map(postScrap -> PostList.of(postScrap.getPost()))
				.collect(Collectors.toList());
		int totalCount = postScrapMapper.countAllPosts(boardId, member.getMemberId());

		return PostListResponseDto.of(pageIndex, pageSize, totalCount, posts);
	}

	@Override
	public PostListResponseDto getMyPost(long memberId, int pageIndex, int pageSize) {
		Member member = getMember(memberId);

		List<PostList> posts = postMapper.findByMember(member.getMemberId(), (pageIndex - 1) * pageSize, pageSize).stream()
				.map(PostList::of)
				.collect(Collectors.toList());
		int totalCount = postMapper.countAllPostsByMember(member.getMemberId());

		return PostListResponseDto.of(pageIndex, pageSize, totalCount, posts);
	}


	private Member getMember(long memberId) {
		return memberMapper.findById(memberId);
	}
}
