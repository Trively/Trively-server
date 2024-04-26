package com.jida.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponse;
import com.jida.dto.res.post.PostDetailResponseDto;
import com.jida.service.PostService;

import lombok.RequiredArgsConstructor;

import static com.jida.constants.SuccessCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;
	
	@PostMapping
	public ResponseEntity<PostDetailResponse> savePost(@ModelAttribute PostSaveRequestDto postSaveRequestDto){
		long postId = postService.writePost(postSaveRequestDto);
		PostDetailResponseDto responseDto = postService.viewPost(postId);
		
		return PostDetailResponse.newResponse(POST_SAVE_SUCCESS, responseDto);
	}
}
