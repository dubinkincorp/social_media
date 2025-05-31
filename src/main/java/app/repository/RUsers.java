package app.repository;

import app.shared.entities.Secret;
import app.shared.entities.user.User;
import app.shared.entities.user.UserId;

import java.util.Optional;

public interface Users {
    User register(User user);
    Optional<User> get(UserId id);
    Optional<Secret> getSecret(UserId id);
}
