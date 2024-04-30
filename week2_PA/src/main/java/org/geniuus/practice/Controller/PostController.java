package org.geniuus.practice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Common.dto.SuccessMessage;
import org.geniuus.practice.Common.dto.SuccessStatusResponse;
import org.geniuus.practice.Service.PostService;
import org.geniuus.practice.Service.dto.PostCreateRequest;
import org.geniuus.practice.Service.dto.PostsFindResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/post")
    public ResponseEntity<SuccessStatusResponse<Void>> createPost(
            @RequestHeader Long memberId,
            @RequestHeader Long blogId,
            @Valid @RequestBody PostCreateRequest postCreateRequest) {

        return ResponseEntity.status(HttpStatus.CREATED).header(
                "Location",
                postService.create(memberId, blogId, postCreateRequest))
                .body(SuccessStatusResponse.of(SuccessMessage.POST_CREATE_SUCCESS));
    }
    // 전체 공개인 포스팅만 모두 조회 + 내 건 비공개여도 조회 가능
    @GetMapping("/post")
    public ResponseEntity<SuccessStatusResponse<List<PostsFindResponse>>> retrievePost(
            @RequestHeader Long memberId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(SuccessStatusResponse.of(SuccessMessage.POST_FIND_SUCCESS, postService.findMultiplePosts(memberId)));
    }
}
