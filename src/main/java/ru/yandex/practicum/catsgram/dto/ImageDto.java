package ru.yandex.practicum.catsgram.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ImageDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    Long id;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    long postId;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String originalFileName;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String filePath;
}