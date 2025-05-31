package app.entities.user;

import app.entities.*;

public record User(UserId id,
                   Login login,
                   UserName name,
                   UserSurname surname,
                   UserBirthDate birthDate,
                   UserGender gender,
                   UserInterests interests,
                   UserCity city,
                   Password password) {
}
