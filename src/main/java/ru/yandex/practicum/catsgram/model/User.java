package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
    Long id;
    String username;
    @EqualsAndHashCode.Include
    String email;
    String password;
    Instant registrationDate;
}
