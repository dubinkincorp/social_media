package app.shared.entities;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Login(String value) {
}
