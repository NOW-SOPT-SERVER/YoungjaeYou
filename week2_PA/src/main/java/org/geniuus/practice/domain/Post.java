package org.geniuus.practice.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.geniuus.practice.Service.dto.PostCreateRequest;

@Entity
@Getter
@NoArgsConstructor
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Blog blog;

    private Boolean visibility;

    @Builder
    public Post(String title, String content, Blog blog, Boolean visibility) {
        this.title = title;
        this.content = content;
        this.blog = blog;
        this.visibility = visibility;
    }

    public Post(Post post) {
        this.title = post.getTitle();
        this.content = post.getContent();
        this.blog = post.getBlog();
    }

    public static Post create(Blog blog, PostCreateRequest postCreateRequest) {
        return Post.builder()
                .blog(blog)
                .title(postCreateRequest.title())
                .content(postCreateRequest.contents())
                .visibility(postCreateRequest.visibility() == null ? true : postCreateRequest.visibility())
                .build();
    }
}
