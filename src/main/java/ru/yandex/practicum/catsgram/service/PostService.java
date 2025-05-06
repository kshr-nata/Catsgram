package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import ru.yandex.practicum.catsgram.dal.PostRepository;
import ru.yandex.practicum.catsgram.dto.NewPostRequest;
import ru.yandex.practicum.catsgram.dto.PostDto;
import ru.yandex.practicum.catsgram.dto.UpdatePostRequest;
import ru.yandex.practicum.catsgram.dto.UserDto;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.mapper.PostMapper;
import ru.yandex.practicum.catsgram.model.Post;

import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// Указываем, что класс PostService - является бином и его
// нужно добавить в контекст приложения
@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserService userService;

    public PostService(UserService userService, PostRepository postRepository) {
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public Collection<PostDto> findAll(int from, int size, String sort) {
        return postRepository.findAll()
                .stream()
                .sorted((p0, p1) -> {
            int comp = p0.getPostDate().compareTo(p1.getPostDate()); //прямой порядок сортировки
            if(sort.equals("desc")){
                comp = -1 * comp; //обратный порядок сортировки
            }
            return comp;
        }).skip(from).limit(size)
                .map(PostMapper::mapToPostDto)
                .collect(Collectors.toList());
    }

    public PostDto create(NewPostRequest request) {
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new ConditionsNotMetException("Описание не может быть пустым");
        }

        UserDto author = userService.getUserById(request.getAuthorId());
        Post post = PostMapper.mapToPost(request);
        postRepository.save(post);
        return PostMapper.mapToPostDto(post);
    }

    public PostDto update(long postId, UpdatePostRequest request) {
        if (request.getDescription() == null || request.getDescription().isBlank()) {
            throw new ConditionsNotMetException("Описание не может быть пустым");
        }
        Post updatedPost = postRepository.findById(postId)
                .map(post -> PostMapper.updateDescription(post, request))
                .orElseThrow(() -> new NotFoundException("Пост с id " + postId + " не найден"));
        updatedPost = postRepository.update(updatedPost);
        return PostMapper.mapToPostDto(updatedPost);
    }

    public Optional<PostDto> findPostById(long postId) {
        return postRepository.findById(postId).map(PostMapper::mapToPostDto);
    }

}