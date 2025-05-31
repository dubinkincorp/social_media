package app.shared.entities.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;

import java.util.UUID;

@Serdeable
public record UserId(@JsonProperty("user_id") UUID id) {
    public UserId(String text) {
        this(UUID.fromString(text));
    }
}
