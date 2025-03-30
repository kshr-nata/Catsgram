package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Post {
    @EqualsAndHashCode.Include
    Long id;
    long authorId;
    String description;
    Instant postDate;
}
