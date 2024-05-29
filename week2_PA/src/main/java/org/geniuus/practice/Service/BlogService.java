package org.geniuus.practice.Service;

import lombok.RequiredArgsConstructor;
import org.geniuus.practice.Common.dto.ErrorMessage;
import org.geniuus.practice.Common.NotFoundException;
import org.geniuus.practice.Service.dto.BlogTitleUpdateRequest;
import org.geniuus.practice.Repository.BlogRepository;
import org.geniuus.practice.Service.dto.BlogCreateRequest;
import org.geniuus.practice.domain.Blog;
import org.geniuus.practice.domain.Member;
import org.geniuus.practice.external.S3Service;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;
    private final MemberService memberService;
    private final S3Service s3Service;
    private static final String BLOG_S3_UPLOAD_FOLDER = "blog/";

//    public String create(Long memberId, BlogCreateRequest createRequest) {
//        // member 찾기
//        Member member = memberService.findById(memberId);
//        Blog blog = blogRepository.save(
//                Blog.create(member, createRequest));
//        return blog.getId().toString();
//    }

    @Transactional
    public String create(Long memberId, BlogCreateRequest createRequest) {
        //member찾기
        Member member = memberService.findById(memberId);
        try {
            Blog blog = blogRepository.save(Blog.create(member, createRequest.title(), createRequest.description(),
                    s3Service.uploadImage(BLOG_S3_UPLOAD_FOLDER, createRequest.image())));
            return blog.getId().toString();
        } catch (RuntimeException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }
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