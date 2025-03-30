package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Image {
    @EqualsAndHashCode.Include
    Long id;
    long postId;
    String originalFileName;
    String filePath;
}
