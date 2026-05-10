package dev.idinaldo.brabank.account.adapters.mappers;

import dev.idinaldo.brabank.account.adapters.out.entities.JpaUser;
import dev.idinaldo.brabank.account.application.ports.IUserMapper;
import dev.idinaldo.brabank.account.domain.models.User;
import org.springframework.stereotype.Component;

@Component
public class IUserMapperImpl implements IUserMapper {

    @Override
    public JpaUser domainToEntity(User user) {
        JpaUser jpaUser = new JpaUser();

        // NOTE: useful when updating entity
        if (user.getId() != null) {
            jpaUser.setId(user.getId());
        }
        jpaUser.setFullName(user.getFullName());
        jpaUser.setBirthDate(user.getBirthDate());
        jpaUser.setEmail(user.getEmail());
        jpaUser.setPhoneNumber(user.getPhoneNumber());
        jpaUser.setCpf(user.getCpf());
        jpaUser.setStatus(user.getStatus());

        return jpaUser;
    }

    @Override
    public User entityToDomain(JpaUser jpaUser) {
        User user = new User();

        user.setId(jpaUser.getId());
        user.setFullName(jpaUser.getFullName());
        user.setBirthDate(jpaUser.getBirthDate());
        user.setEmail(jpaUser.getEmail());
        user.setPhoneNumber(jpaUser.getPhoneNumber());
        user.setCpf(jpaUser.getCpf());
        user.setStatus(jpaUser.getStatus());

        return user;
    }
}
