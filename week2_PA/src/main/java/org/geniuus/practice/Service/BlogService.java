package org.geniuus.practice.Service;

import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Common.dto.ErrorMessage;
import org.geniuus.practice.Common.NotFoundException;
import org.geniuus.practice.Service.dto.BlogTitleUpdateRequest;
import org.geniuus.practice.Repository.BlogRepository;
import org.geniuus.practice.Service.dto.BlogCreateRequest;
import org.geniuus.practice.domain.Blog;
import org.geniuus.practice.domain.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final MemberService memberService;

    public String create(Long memberId, BlogCreateRequest createRequest) {
        // member 찾기
        Member member = memberService.findById(memberId);
        Blog blog = blogRepository.save(
                Blog.create(member, createRequest));
        return blog.getId().toString();
    }

    @Transactional
    public void updateTitle(Long blogId, BlogTitleUpdateRequest blogTitleUpdateRequest) {
        Blog blog = blogRepository.findById(blogId).get();
        blog.updateTitle(blogTitleUpdateRequest.title());
        blogRepository.save(blog);
    }

    public Blog findBlogByMemberId(Long memberId) {
        return blogRepository.findByMemberId(memberId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND_BY_ID_EXCEPTION)
        );
    }

}