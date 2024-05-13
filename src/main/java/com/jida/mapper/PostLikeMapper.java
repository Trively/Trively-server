package com.jida.mapper;

import com.jida.domain.PostLike;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostLikeMapper {
    Optional<PostLike> findByUserAndPost(@Param("memberId") Long memberId,@Param("postId") Long postId);
    void save(PostLike postLike);
    void delete(PostLike postLike);
}
