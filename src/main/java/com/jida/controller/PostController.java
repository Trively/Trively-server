package com.jida.controller;

import com.jida.dto.res.post.PostResponse;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
	public ResponseEntity<PostDetailResponse> modifyPost(@PathVariable("postId") long postId, @RequestBody PostSaveRequestDto postSaveRequestDto) {
		System.out.println(postSaveRequestDto.getBoardName());
		System.out.println(postSaveRequestDto.getContent());
		System.out.println(postSaveRequestDto.getTitle());
		System.out.println(postId);
		long updatedPostId = postService.modifyPost(postId, postSaveRequestDto);
		PostDetailResponseDto responseDto = postService.viewPost(updatedPostId);
		
		return PostDetailResponse.newResponse(POST_UPDATE_SUCCESS, responseDto);
	}

	@DeleteMapping("/{postId}")
	public ResponseEntity<PostResponse> deletePost(@PathVariable("postId") long postId){
		postService.deletePost(postId);
		return PostResponse.newResponse(POST_DELETE_SUCCESS);
	}
}
