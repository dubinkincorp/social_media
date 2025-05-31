package app.shared.entities.user;

import app.shared.entities.Login;
import app.shared.entities.Password;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.annotation.Nullable;

@Serdeable
public record User(UserId id,
                   @JsonProperty("first_name") UserName name,
                   @JsonProperty("second_name") UserSurname surname,
                   @JsonProperty("birthdate") UserBirthDate birthDate,
                   @JsonProperty("gender") UserGender gender,
                   @JsonProperty("biography") UserInterests interests,
                   @JsonProperty("city") UserCity city,
                   @JsonProperty("password") Password password) {
}
