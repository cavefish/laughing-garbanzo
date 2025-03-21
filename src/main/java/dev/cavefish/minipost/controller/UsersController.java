package dev.cavefish.minipost.controller;

import dev.cavefish.minipost.controller.mappers.UsersMapper;
import dev.cavefish.minipost.dto.UserCreateDto;
import dev.cavefish.minipost.dto.UserDto;
import dev.cavefish.minipost.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private UsersService usersService;
    private UsersMapper usersMapper;

    @GetMapping("list")
    public ModelAndView list() {
        List<UserDto> users = usersService.getUsers().stream().map(usersMapper::toDto).toList();
        return new ModelAndView(
                "users-list",
                Map.of("users", users)
        );
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") String alias) {
        boolean success = usersService.deleteUser(alias);
        return success ? new ModelAndView("redirect:/users/list") : error("Cannot delete user %s".formatted(alias));
    }

    @PostMapping("/add")
    public ModelAndView save(@ModelAttribute("userCreateDto") UserCreateDto userCreateDto) {
        boolean success = usersService.createUser(usersMapper.fromDto(userCreateDto));
        return success ? new ModelAndView("redirect:/users/list") : error("Cannot create user %s".formatted(userCreateDto.getAlias()));
    }

    private ModelAndView error(String message) {
        return new ModelAndView("error-info", Map.of("message", message));
    }


}
