package dev.cavefish.minipost.domain.users;

import org.springframework.stereotype.Service;

@Service
public class EmailValidator {

    // Validates following https://datatracker.ietf.org/doc/html/rfc5322#section-3.4.1
    public boolean isValid(String email) {
        // TODO implement this method using TDD
        // https://en.wikipedia.org/wiki/Email_address#Examples
        return true;
    }
}
