package dev.cavefish.minipost.domain.users;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator {
    public boolean isValid(String email) {
        return true;
    }
}
