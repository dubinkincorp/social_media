package app.repository;

import app.shared.entities.Login;
import app.shared.entities.Password;
import app.shared.entities.user.*;
import app.repository.db.DbUsers;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@MicronautTest(rollback = false)
public class DbUsersTest {

    @Inject
    DbUsers dbUsers;

    @Test
    public void registerTest() {
        /*User user = new User(
                null,
                new Login("anna"),
                new UserName("Anna"),
                new UserSurname("Zubkova"),
                new UserBirthDate(LocalDateTime.of(1995, 5, 27, 0, 0)),
                new UserGender("female"),
                new UserInterests("Jane Austen"),
                new UserCity("Penza"),
                new Password("123456789")
        );
        Optional<UserId> registered = dbUsers.create(user);
        Assertions.assertTrue(registered.isPresent());*/
    }

    @Test
    public void getTest() {
        Optional<User> maybeUser = dbUsers.read(new UserId("c0ebfc59-6220-4dae-a417-0bf2803e0599"));
        Assertions.assertTrue(maybeUser.isPresent());
    }
}
