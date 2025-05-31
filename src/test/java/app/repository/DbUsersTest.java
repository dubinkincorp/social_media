import app.entities.Login;
import app.entities.Password;
import app.entities.user.*;
import app.repository.db.DbUsers;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

@MicronautTest
public class DbUsersTest {

    @Inject
    DbUsers dbUsers;

    @Test
    public void registerTest() {
        User user = new User(
                null,
                new Login("login"),
                new UserName("name"),
                new UserSurname("surname"),
                new UserBirthDate(LocalDateTime.of(1995, 5, 27, 0, 0)),
                new UserGender("female"),
                new UserInterests("Jane Austen"),
                new UserCity("city"),
                new Password("123456789")
        );
        User registered = dbUsers.register(user);
        Assertions.assertNotNull(registered);
    }
}
