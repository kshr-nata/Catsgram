package ru.yandex.practicum.catsgram.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.dto.*;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.Instant;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PostMapper {
    public static Post mapToPost(NewPostRequest request) {
        Post post = new Post();
        post.setDescription(request.getDescription());
        post.setAuthorId(request.getAuthorId());
        post.setPostDate(Instant.now());
        return post;
    }

    public static PostDto mapToPostDto(Post post) {
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setAuthorId(post.getAuthorId());
        dto.setDescription(post.getDescription());
        dto.setPostDate(Instant.now());
        return dto;
    }

    public static Post updateDescription(Post post, UpdatePostRequest request) {
        post.setDescription(request.getDescription());
        return post;
    }
}