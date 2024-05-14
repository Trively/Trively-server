package com.jida.mapper;

import com.jida.domain.Post;
import com.jida.domain.PostScrap;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostScrapMapper {
    Optional<PostScrap> findByUserAndPost(Long memberId, Long postId);

    void save(PostScrap postScrap);
    void delete(PostScrap postScrap);
    List<PostScrap> findScraps(String order, long boardId, long memberId, int offset, int limit);
    int countAllPosts(long boardId, long memberId);
}
