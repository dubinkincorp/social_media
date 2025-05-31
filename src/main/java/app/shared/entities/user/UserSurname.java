package app.shared.entities.user;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record UserSurname(String value) {
}
