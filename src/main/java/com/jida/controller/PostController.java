package com.jida.controller;

import static com.jida.constants.SuccessCode.POST_DETAIL_SUCCESS;
import static com.jida.constants.SuccessCode.POST_SAVE_SUCCESS;
import static com.jida.constants.SuccessCode.POST_UPDATE_SUCCESS;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponse;
import com.jida.dto.res.post.PostDetailResponseDto;
import com.jida.service.PostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
	private final PostService postService;
	
	@PostMapping
	public ResponseEntity<PostDetailResponse> savePost(@RequestBody PostSaveRequestDto postSaveRequestDto){
		long postId = postService.writePost(postSaveRequestDto);
		PostDetailResponseDto responseDto = postService.viewPost(postId);
		
		return PostDetailResponse.newResponse(POST_SAVE_SUCCESS, responseDto);
	}
	
	@GetMapping("/{postId}")
	public ResponseEntity<PostDetailResponse> viewPost(@PathVariable("postId") long postId) {
		PostDetailResponseDto responseDto = postService.viewPost(postId);
		
		return PostDetailResponse.newResponse(POST_DETAIL_SUCCESS, responseDto);
	}
	
	@PutMapping("/{postId}")
	public ResponseEntity<PostDetailResponse> viewPost(@PathVariable("postId") long postId, @RequestBody PostSaveRequestDto postSaveRequestDto) {
		System.out.println(postSaveRequestDto.getBoardName());
		System.out.println(postSaveRequestDto.getContent());
		System.out.println(postSaveRequestDto.getTitle());
		System.out.println(postId);
		long updatedPostId = postService.modifyPost(postId, postSaveRequestDto);
		PostDetailResponseDto responseDto = postService.viewPost(updatedPostId);
		
		return PostDetailResponse.newResponse(POST_UPDATE_SUCCESS, responseDto);
	}
}
