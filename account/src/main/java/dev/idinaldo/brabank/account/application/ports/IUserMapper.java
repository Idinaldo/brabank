package dev.idinaldo.brabank.account.application.ports;

import dev.idinaldo.brabank.account.adapters.out.entities.JpaUser;
import dev.idinaldo.brabank.account.domain.models.User;

public interface IUserMapper {

    JpaUser domainToEntity(User user);
    User entityToDomain(JpaUser jpaUser);
}
