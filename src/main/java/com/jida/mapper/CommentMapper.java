package com.jida.mapper;

import com.jida.domain.Comment;
import com.jida.domain.Member;
import com.jida.domain.Post;
import com.jida.dto.req.CommentSaveRequestDto;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    Optional<Comment> findById(Long commentId);

    void save(Comment comment);

    List<Comment> findAll(Long postId);

    void delete(Long commentId);
    List<Comment> findByMember(long memberId);
}
