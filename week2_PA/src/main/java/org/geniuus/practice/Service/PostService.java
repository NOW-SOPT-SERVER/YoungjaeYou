package org.geniuus.practice.Service;

import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Common.ForbiddenException;
import org.geniuus.practice.Common.dto.ErrorMessage;
import org.geniuus.practice.Common.NotFoundException;
import org.geniuus.practice.Repository.PostRepository;
import org.geniuus.practice.Service.dto.PostCreateRequest;
import org.geniuus.practice.Service.dto.PostsFindResponse;
import org.geniuus.practice.domain.Blog;
import org.geniuus.practice.domain.Post;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final BlogService blogService;

    public String create(Long memberId, Long blogId, PostCreateRequest postCreateRequest) {
        // 블로그의 주인이 맞는지 권한 검사
        Blog blog = blogService.findBlogByMemberId(memberId);

        if (!isOwner(blogId, blog.getId())) throw new ForbiddenException(ErrorMessage.BLOG_FORBIDDEN_BY_MEMBER_EXCEPTION);

        Post post = postRepository.save(Post.create(blog, postCreateRequest));
        return post.getId().toString();
    }

    public List<PostsFindResponse> findMultiplePosts(Long memberId) {
        Blog blog = blogService.findBlogByMemberId(memberId);
        Specification<Post> spec = PostRepository.findVisiblePostsById(blog.getId());
        if(postRepository.findAll(spec).isEmpty()) {
            throw new NotFoundException(ErrorMessage.POST_NOT_FOUND_BY_ID_EXCEPTION);
        }
        return PostsFindResponse.listOf(postRepository.findAll(spec));
    }

    public boolean isOwner(Long memberId, Long blogId) {
        return blogId.equals(memberId);
    }
}
