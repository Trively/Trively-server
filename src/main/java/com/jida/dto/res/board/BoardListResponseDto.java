package com.jida.dto.res.board;

import java.util.List;

import com.jida.domain.Board;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardListResponseDto {
	private List<BoardList> boards;
	
	private BoardListResponseDto(List<BoardList> boards) {
		this.boards = boards;
	}
	
	public static BoardListResponseDto of(List<BoardList> boards) {
		 return new BoardListResponseDto(boards);
	}
	
	@Getter
	public static class BoardList {
		private Long boardId;
		private String boardName;
		
		public BoardList(Board entity) {
			this.boardId = entity.getBoardId();
			this.boardName = entity.getBoardName();
		}
	}
}
