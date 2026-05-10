package dev.idinaldo.brabank.account.adapters.mappers;

import dev.idinaldo.brabank.account.adapters.out.entities.JpaUser;
import dev.idinaldo.brabank.account.domain.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public JpaUser domainToEntity(User user) {
        JpaUser jpaUser = new JpaUser();

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
