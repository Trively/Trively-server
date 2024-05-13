package com.jida.controller;

import static com.jida.constants.SuccessCode.POST_DELETE_SUCCESS;
import static com.jida.constants.SuccessCode.POST_DETAIL_SUCCESS;
import static com.jida.constants.SuccessCode.POST_LIKE_CANCELED;
import static com.jida.constants.SuccessCode.POST_LIKE_SUCCESS;
import static com.jida.constants.SuccessCode.POST_LIST_SUCCESS;
import static com.jida.constants.SuccessCode.POST_SAVE_SUCCESS;
import static com.jida.constants.SuccessCode.POST_UPDATE_SUCCESS;

import com.jida.dto.req.PostSaveRequestDto;
import com.jida.dto.res.post.PostDetailResponse;
import com.jida.dto.res.post.PostDetailResponseDto;
import com.jida.dto.res.post.PostListResponse;
import com.jida.dto.res.post.PostListResponseDto;
import com.jida.dto.res.post.PostResponse;
import com.jida.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostDetailResponse> savePost(@RequestBody PostSaveRequestDto postSaveRequestDto) {
        long postId = postService.writePost(postSaveRequestDto);
        PostDetailResponseDto responseDto = postService.viewPost(postId);

        return PostDetailResponse.newResponse(POST_SAVE_SUCCESS, responseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> viewPost(@PathVariable("postId") long postId) {
        PostDetailResponseDto responseDto = postService.viewPost(postId);
        postService.updateHit(postId);
        return PostDetailResponse.newResponse(POST_DETAIL_SUCCESS, responseDto);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDetailResponse> modifyPost(@PathVariable("postId") long postId,
                                                         @RequestBody PostSaveRequestDto postSaveRequestDto) {
        long updatedPostId = postService.modifyPost(postId, postSaveRequestDto);
        PostDetailResponseDto responseDto = postService.viewPost(updatedPostId);

        return PostDetailResponse.newResponse(POST_UPDATE_SUCCESS, responseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<PostResponse> deletePost(@PathVariable("postId") long postId) {
        postService.deletePost(postId);
        return PostResponse.newResponse(POST_DELETE_SUCCESS);
    }

    @GetMapping
    public ResponseEntity<PostListResponse> showList(
            @RequestParam(name = "order", defaultValue = "latest") String order,
            @RequestParam(name = "boardId", defaultValue = "0") long boardId,
            @RequestParam(name = "pageIndex", defaultValue = "1") int pageIndex,
            @RequestParam(name = "pageSize", defaultValue = "10") int pageSize) {
        PostListResponseDto responseDto = postService.showList(order, boardId, pageIndex, pageSize);

        return PostListResponse.newResponse(POST_LIST_SUCCESS, responseDto);
    }

    @PostMapping("/{postId}/like")
    public ResponseEntity<PostResponse> postLike(@PathVariable Long postId) {
        boolean response = postService.clickPostLike(postId);
        if (response) {
			return PostResponse.newResponse(POST_LIKE_SUCCESS);
        }
		return PostResponse.newResponse(POST_LIKE_CANCELED);
    }
}
