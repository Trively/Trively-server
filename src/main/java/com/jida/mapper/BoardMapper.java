package com.jida.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.jida.domain.Board;

@Mapper
public interface BoardMapper {
	List<Board> showList();
	long findId(String boardName);
}
