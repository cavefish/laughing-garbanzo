package dev.cavefish.minipost.controller;

import dev.cavefish.minipost.controller.mappers.PostsMapper;
import dev.cavefish.minipost.controller.mappers.TagsMapper;
import dev.cavefish.minipost.controller.mappers.UsersMapper;
import dev.cavefish.minipost.dto.*;
import dev.cavefish.minipost.services.PostsService;
import dev.cavefish.minipost.services.TagsService;
import dev.cavefish.minipost.services.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/posts")
@AllArgsConstructor
public class PostsController {

    private PostsService postsService;
    private PostsMapper postsMapper;

    private UsersService usersService;
    private UsersMapper usersMapper;

    @GetMapping
    public ModelAndView getPosts(
            @RequestParam(value = "createdByAlias", required = false) String createdBy,
            @RequestParam(value = "tags", required = false) String tags,
            @RequestParam(value = "fromDate", required = false) LocalDateTime fromDate,
            @RequestParam(value = "toDate", required = false) LocalDateTime toDate
    ) {
        List<PostDto> posts = postsService.searchPosts(
                createdBy, tags, fromDate, toDate
        ).stream().map(postsMapper::toDto).toList();
        List<UserDto> users = usersService.getUsers().stream().map(usersMapper::toDto).toList();
        return new ModelAndView(
                "posts-list",
                Map.of(
                        "posts", posts,
                        "users", users
                ));
    }

    @PostMapping("/add")
    public ModelAndView save(@ModelAttribute("postCreateDto") PostCreateDto postCreateDto) {
        boolean success = postsService.create(postsMapper.fromDto(postCreateDto));
        return success ? new ModelAndView("redirect:/posts") : error("Cannot create post %s".formatted(postCreateDto.getTitle()));
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") String id) {
        boolean success = postsService.deletePost(UUID.fromString(id));
        return success ? new ModelAndView("redirect:/posts") : error("Cannot delete post %d".formatted(id));
    }

    private ModelAndView error(String message) {
        return new ModelAndView("error-info", Map.of("message", message));
    }


}
