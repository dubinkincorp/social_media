package app.security;

import app.entities.Secret;
import app.entities.user.UserId;
import app.repository.db.DbUsers;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import io.micronaut.http.HttpRequest;
import io.micronaut.security.authentication.AuthenticationRequest;
import io.micronaut.security.authentication.AuthenticationResponse;
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider;
import jakarta.inject.Singleton;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

@Singleton
public class AuthenticationProviderUserPassword<B> implements HttpRequestAuthenticationProvider<B> {

    private final DbUsers dbUsers;

    public AuthenticationProviderUserPassword(DbUsers dbUsers) {
        this.dbUsers = dbUsers;
    }

    @Override
    public @NonNull AuthenticationResponse authenticate(@Nullable HttpRequest<B> requestContext,
                                                        @NonNull AuthenticationRequest<String, String> authRequest) {
        String id = authRequest.getIdentity().toString();
        Optional<Secret> maybeSecret = dbUsers.getSecret(new UserId(id));
        if (maybeSecret.isEmpty()) {
            return AuthenticationResponse.failure("No user found for " + id);
        }
        Secret secret = maybeSecret.get();
        try {
            if (Passwords.checkPassword(authRequest.getSecret().toString().toCharArray(), secret.salt(), secret.hash())) {
                return AuthenticationResponse.success(id);
            } else {
                return AuthenticationResponse.failure();
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
