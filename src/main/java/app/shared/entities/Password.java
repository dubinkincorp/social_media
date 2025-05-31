package app.shared.entities;

import app.security.Passwords;
import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public record Password(String value) {

    public byte[] generateHash(byte[] salt) throws Exception {
        return Passwords.generateHash(value.toCharArray(), salt);
    }
}
