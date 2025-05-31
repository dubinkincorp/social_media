package app.repository;

import app.shared.entities.Secret;
import app.shared.entities.user.User;
import app.shared.entities.user.UserId;

import java.util.Optional;

public interface RUsers {
    Optional<UserId> create(User user);
    Optional<User> read(UserId id);
    Optional<Secret> getSecret(UserId id);
}
