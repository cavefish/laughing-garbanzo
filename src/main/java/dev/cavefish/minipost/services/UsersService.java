package dev.cavefish.minipost.services;

import dev.cavefish.minipost.domain.users.EmailValidator;
import dev.cavefish.minipost.domain.users.PasswordEncryptor;
import dev.cavefish.minipost.domain.users.User;
import dev.cavefish.minipost.domain.users.UserCreateRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {

    private final PasswordEncryptor passwordEncryptor;
    private final EmailValidator emailValidator;

    public List<User> getUsers() {
        // TODO Add query to DB to retrieve all users
        return List.of(
                new User("foo",  "foo@domain.com", "Fabian O.", "Other"),
                new User("bar",  "bar@domain.com", "Beckett A.", "Robot")
        );
    }

    public boolean deleteUser(String alias) {
        // TODO add query to DB to delete user. Return true if success.
        return false;
    }

    public boolean createUser(UserCreateRequest userCreateRequest) {
        // TODO add query to DB to create user. Return true if success.
        return false;
    }
}
