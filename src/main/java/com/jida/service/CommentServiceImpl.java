package com.jida.service;

import static com.jida.constants.ExceptionCode.COMMENT_CANT_DELETE;
import static com.jida.constants.ExceptionCode.COMMENT_NOT_FOUND;
import static com.jida.constants.ExceptionCode.POST_NOT_FOUND;
import static com.jida.constants.ExceptionCode.RE_COMMENT_ONLY;

import com.jida.domain.Comment;
import com.jida.domain.Member;
import com.jida.domain.Post;
import com.jida.dto.req.CommentSaveRequestDto;
import com.jida.dto.res.comment.CommentDetailResponseDto;
import com.jida.dto.res.comment.CommentListResponseDto;
import com.jida.dto.res.comment.CommentListResponseDto.CommentList;
import com.jida.exception.CustomException;
import com.jida.mapper.CommentMapper;
import com.jida.mapper.MemberMapper;
import com.jida.mapper.PostMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final MemberMapper memberMapper;
    private final PostMapper postMapper;

    @Override
    public CommentDetailResponseDto save(long memberId, CommentSaveRequestDto commentSaveRequestDto) {
        Member member = getMember(memberId);
        Long parentId = commentSaveRequestDto.getParentId();
        Long postId = commentSaveRequestDto.getPostId();
        Post post = postMapper.findById(postId)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));

        //대댓글
        if (parentId != null) {
            Comment comment = commentMapper.findById(parentId)
                    .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));
            //대댓글에 대댓글 작성 시 최상위 부모를 부모로 지정
            if(comment.getParentId() != null) {
                parentId = comment.getParentId();
            }
        }

        Comment comment = Comment.createComment(post, member, parentId, commentSaveRequestDto.getContent());
        commentMapper.save(comment);
        postMapper.addCommentCnt(postId);

        return CommentDetailResponseDto.of(comment);
    }

    @Override
    public CommentListResponseDto findAll(long memberId, Long postId) {
        Post post = postMapper.findById(postId)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        Member member = getMember(memberId);

        List<CommentList> comments = commentMapper.findAll(postId).stream()
                .map(comment -> CommentList.of(comment,
                        comment.getMember().getMemberId() == member.getMemberId()))
                .toList();

        return CommentListResponseDto.of(comments);
    }

    @Override
    public void delete(long memberId, Long postId, Long commentId) {
        Member member = getMember(memberId);
        Post post = postMapper.findById(postId)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        Comment comment = commentMapper.findById(commentId)
                .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));

        if (comment.getMember().getMemberId() != member.getMemberId()) {
            throw new CustomException(COMMENT_CANT_DELETE);
        }

        postMapper.diffCommentCnt(postId, commentId);
        commentMapper.delete(commentId);
    }

    @Override
    public CommentListResponseDto findByMember(long memberId) {
        List<CommentList> comments = commentMapper.findByMember(memberId).stream()
                .map(comment -> CommentList.of(comment, true))
                .toList();

        return CommentListResponseDto.of(comments);
    }


    private Member getMember(long memberId) {
        return memberMapper.findById(memberId);
    }
}
