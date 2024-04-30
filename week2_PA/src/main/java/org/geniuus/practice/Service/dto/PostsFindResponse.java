package org.geniuus.practice.Service.dto;

import org.geniuus.practice.domain.Post;

import java.util.List;
import java.util.stream.Collectors;

public record PostsFindResponse(
        String title,
        String contents
) {

    public static List<PostsFindResponse> listOf(final List<Post> posts) {
        return posts.stream()
                .map(post -> new PostsFindResponse(post.getTitle(), post.getContent()))
                .toList();
    }

}
