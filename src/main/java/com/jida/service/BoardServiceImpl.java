package com.jida.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.jida.dto.res.board.BoardListResponseDto;
import com.jida.dto.res.board.BoardListResponseDto.BoardList;
import com.jida.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;

	@Override
	public BoardListResponseDto showList() {
		List<BoardList> boards = boardMapper.showList().stream()
				.map(BoardList::new)
				.collect(Collectors.toList());
		return BoardListResponseDto.of(boards);
	}

}
