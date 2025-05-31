package app.endpoints;

import app.repository.RUsers;
import app.shared.entities.user.User;
import app.shared.entities.user.UserId;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.rules.SecurityRule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller("/user")
public class EUser {

    private static final Logger LOG = LogManager.getLogger(EUser.class);

    private final RUsers users;

    public EUser(RUsers users) {
        this.users = users;
    }

    @Post("/register")
    @Secured(SecurityRule.IS_ANONYMOUS)
    public UserId register(@Body User user) {
        LOG.info("Start user registration");
        return users.create(user).orElseThrow();
    }

    @Get("/get/{id}")
    @Secured(SecurityRule.IS_AUTHENTICATED)
    public User get(String id) {
        return users.read(new UserId(id)).orElseThrow();
    }
}
