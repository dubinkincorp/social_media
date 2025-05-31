package app.shared.entities;

public record Secret(byte[] salt, byte[] hash) {
}
