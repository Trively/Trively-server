package com.jida.dto.req;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSaveRequestDto {
	private String title;
	private String content;
	private String boardName;
}
