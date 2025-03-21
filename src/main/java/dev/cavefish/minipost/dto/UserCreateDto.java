package dev.cavefish.minipost.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private String alias;
    private String password;
    private String email;
    private String firstName;
    private String lastName;
}
