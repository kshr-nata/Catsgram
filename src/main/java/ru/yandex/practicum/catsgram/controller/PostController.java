package ru.yandex.practicum.catsgram.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.dto.NewPostRequest;
import ru.yandex.practicum.catsgram.dto.PostDto;
import ru.yandex.practicum.catsgram.dto.UpdatePostRequest;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.service.PostService;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Collection<PostDto> findAll(@RequestParam(defaultValue = "desc") String sort, @RequestParam(defaultValue = "10") int size, @RequestParam (defaultValue = "0") int from) {
        if (!sort.equals("desc") && !sort.equals("asc")) {
            throw new ParameterNotValidException("sort", "Получено: " + sort + " должно быть: ask или desc");
        }
        if (size <= 0) {
            throw new ParameterNotValidException("size", "Размер должен быть больше нуля");
        }
        if (from < 0) {
            throw new ParameterNotValidException("from", "Начало выборки должно быть положительным числом");
        }
        return postService.findAll(from, size, sort);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto create(@RequestBody NewPostRequest request) {
        return postService.create(request);
    }

    @PutMapping("/{postId}")
    public PostDto update(@PathVariable("postId") long postId, @RequestBody UpdatePostRequest request) {
        return postService.update(postId, request);
    }

    @GetMapping("/{postId}")
    public PostDto findById(@PathVariable long postId) {
        Optional<PostDto> dto = postService.findPostById(postId);
        if (dto.isEmpty()) {
            throw new NotFoundException("Пост с id = " + postId + " не найден");
        } else {
            return dto.get();
        }
    }
}