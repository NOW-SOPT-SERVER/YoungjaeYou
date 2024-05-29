package org.geniuus.practice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Service.dto.BlogTitleUpdateRequest;
import org.geniuus.practice.Common.dto.SuccessMessage;
import org.geniuus.practice.Common.dto.SuccessStatusResponse;
import org.geniuus.practice.Service.BlogService;
import org.geniuus.practice.Service.dto.BlogCreateRequest;
import org.geniuus.practice.auth.PrincipalHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;
    private final PrincipalHandler principalHandler;

//    @PostMapping("/blog")
//    public ResponseEntity<SuccessStatusResponse> createBlog(
//            @RequestHeader Long memberId,
//            @RequestBody BlogCreateRequest blogCreateRequest) {
//        return ResponseEntity.status(HttpStatus.CREATED).header(
//                        "Location",
//                        blogService.create(memberId, blogCreateRequest))
//                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
//    }

    @PostMapping("/blog")
    public ResponseEntity createBlog(
            @ModelAttribute BlogCreateRequest blogCreateRequest
    ) {
        return ResponseEntity.created(URI.create(blogService.create(
                principalHandler.getUserIdFromPrincipal(), blogCreateRequest))).build();
    }

    @PatchMapping("/blog/{blogId}/title")
    public ResponseEntity updateBlogTitle(
            @PathVariable Long blogId,
            @Valid @RequestBody BlogTitleUpdateRequest blogTitleUpdateRequest
    ) {
        blogService.updateTitle(blogId, blogTitleUpdateRequest);
        return ResponseEntity.noContent().build();
    }

}


