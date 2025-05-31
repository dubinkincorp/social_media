package app.entities;

import app.security.Passwords;

public record Password(String value) {

    public byte[] generateHash(byte[] salt) throws Exception {
        return Passwords.generateHash(value.toCharArray(), salt);
    }
}
