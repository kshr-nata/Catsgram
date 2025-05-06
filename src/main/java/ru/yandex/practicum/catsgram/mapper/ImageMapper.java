package ru.yandex.practicum.catsgram.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.yandex.practicum.catsgram.dto.*;
import ru.yandex.practicum.catsgram.model.Image;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ImageMapper {
    public static Image mapToImage(NewImageRequest request) {
        Image image = new Image();
        image.setFilePath(request.getFilePath());
        image.setPostId(request.getPostId());
        image.setOriginalFileName(request.getOriginalFileName());
        return image;
    }

    public static ImageDto mapToImageDto(Image image) {
        ImageDto dto = new ImageDto();
        dto.setId(image.getId());
        dto.setFilePath(image.getFilePath());
        dto.setOriginalFileName(image.getOriginalFileName());
        dto.setPostId(image.getPostId());
        return dto;
    }

}