package dev.cavefish.minipost.domain.users;

public record UserCreateRequest(String alias, String password, String email, String firstName, String lastName) {
}
