package app.entities;

public record Secret(byte[] salt, byte[] hash) {
}
