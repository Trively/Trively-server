package com.jida.service;

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
import com.jida.mapper.PostMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentMapper commentMapper;
    private final PostMapper postMapper;

    @Override
    public void save(Long postId, Long parentId, CommentSaveRequestDto commentSaveRequestDto) {
        Member member = getMember();
        Post post = postMapper.findById(postId)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));

        //대댓글
        if (parentId != null) {
            commentMapper.findById(parentId)
                    .orElseThrow(() -> new CustomException(COMMENT_NOT_FOUND));
            //대댓글에는 대댓글 불가능
            if (commentMapper.findById(parentId).get().getParentId() != null) {
                throw new CustomException(RE_COMMENT_ONLY);
            }
        }

        Comment comment = Comment.createComment(post, member, parentId, commentSaveRequestDto.getContent());
        commentMapper.save(comment);
    }

    @Override
    public CommentListResponseDto findAll(Long postId) {
        Post post = postMapper.findById(postId)
                .orElseThrow(() -> new CustomException(POST_NOT_FOUND));
        Member member = getMember();

        List<CommentList> comments = commentMapper.findAll(postId).stream()
                .map(comment -> CommentList.of(comment,
                        comment.getMember().getMemberId() == member.getMemberId()))
                .toList();

        return CommentListResponseDto.of(comments);
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
