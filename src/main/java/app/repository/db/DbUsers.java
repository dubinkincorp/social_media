package app.repository.db;

import app.shared.entities.Login;
import app.shared.entities.Secret;
import app.shared.entities.user.*;
import app.security.Passwords;
import app.repository.RUsers;
import io.micronaut.transaction.TransactionOperations;
import jakarta.inject.Singleton;

import java.sql.*;
import java.util.Optional;

@Singleton
public class DbUsers implements RUsers {

    private final Connection connection;
    private final TransactionOperations<Connection> transactionManager;

    public DbUsers(Connection connection, TransactionOperations<Connection> transactionManager) {
        this.connection = connection;
        this.transactionManager = transactionManager;
    }

    @Override
    public Optional<UserId> create(User user) {
        return transactionManager.executeWrite(status -> {
            Optional<UserId> userId;
            try (PreparedStatement ps = connection.prepareStatement(
                    "insert into users (name, surname, birth_date, gender, interests, city, salt, password) values (?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            )) {
                ps.setString(1, user.name().value());
                ps.setString(2, user.surname().value());
                ps.setTimestamp(3, Timestamp.valueOf(user.birthDate().value()));
                ps.setString(4, user.gender().value());
                ps.setString(5, user.interests().value());
                ps.setString(6, user.city().value());
                byte[] salt = Passwords.generateSalt();
                ps.setBytes(7, salt);
                ps.setBytes(8, user.password().generateHash(salt));
                ps.execute();

                try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        userId = Optional.of(new UserId(generatedKeys.getObject(1).toString()));
                    } else {
                        userId = Optional.empty();
                    }
                }
            }
            return userId;
        });
    }

    @Override
    public Optional<User> read(UserId id) {
        return transactionManager.executeRead(status -> {
            try (PreparedStatement ps = connection.prepareStatement("select * from users where id=?")) {
                ps.setObject(1, id.id());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(new User(
                                new UserId(rs.getObject("id").toString()),
                                new UserName(rs.getString("name")),
                                new UserSurname(rs.getString("surname")),
                                new UserBirthDate(rs.getDate("birth_date").toLocalDate().atStartOfDay()),
                                new UserGender(rs.getString("gender")),
                                new UserInterests(rs.getString("interests")),
                                new UserCity(rs.getString("city")),
                                null
                        ));
                    } else {
                        return Optional.empty();
                    }
                }
            }
        });
    }

    @Override
    public Optional<Secret> getSecret(UserId id) {
        return transactionManager.executeRead(status -> {
            try (PreparedStatement ps = connection.prepareStatement("select salt, password from users where id=?")) {
                ps.setObject(1, id.id());
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return Optional.of(new Secret(
                                rs.getBytes("salt"),
                                rs.getBytes("password")
                        ));
                    } else {
                        return Optional.empty();
                    }
                }
            }
        });
    }
}
