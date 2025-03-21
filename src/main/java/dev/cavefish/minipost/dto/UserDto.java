package dev.cavefish.minipost.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String alias;
    private String email;
    private String firstName;
    private String lastName;
}
