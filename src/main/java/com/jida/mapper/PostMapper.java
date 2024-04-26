package com.jida.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.jida.domain.Post;

@Mapper
public interface PostMapper {
	void writePost(Post post);
	Post findById(long postId);
}
