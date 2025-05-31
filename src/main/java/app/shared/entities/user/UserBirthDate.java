package app.shared.entities.user;

import io.micronaut.serde.annotation.Serdeable;

import java.time.LocalDateTime;

@Serdeable
public record UserBirthDate(LocalDateTime value) {
}
