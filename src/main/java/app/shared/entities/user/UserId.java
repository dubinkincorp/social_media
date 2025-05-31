package app.entities.user;

import java.util.UUID;

public record UserId(UUID id) {
    public UserId(String text) {
        this(UUID.fromString(text));
    }
}
