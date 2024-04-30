package org.geniuus.practice.Service.dto;

import org.geniuus.practice.domain.Post;

public record PostFindResponse(
        String title,
        String contents
) {
    public static PostFindResponse of(Post post) {
        return new PostFindResponse(post.getTitle(), post.getContent());
    }
}
