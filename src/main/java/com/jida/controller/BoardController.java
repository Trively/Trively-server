package com.jida.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jida.dto.res.board.BoardListResponse;
import com.jida.dto.res.board.BoardListResponseDto;
import com.jida.service.BoardService;

import lombok.RequiredArgsConstructor;

import static com.jida.constants.SuccessCode.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/board")
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping
	public ResponseEntity<BoardListResponse> showList() {
		BoardListResponseDto responseDto = boardService.showList();
		
		return BoardListResponse.newResponse(BOARD_READ_SUCCESS, responseDto);
	}
}
