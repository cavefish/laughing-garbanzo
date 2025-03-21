package dev.cavefish.minipost.controller;

import dev.cavefish.minipost.controller.mappers.TagsMapper;
import dev.cavefish.minipost.dto.TagCreateDto;
import dev.cavefish.minipost.dto.TagDto;
import dev.cavefish.minipost.services.TagsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/tags")
@AllArgsConstructor
public class TagsController {

    private TagsService tagsService;
    private TagsMapper tagsMapper;

    @GetMapping("/list")
    public ModelAndView list() {
        List<TagDto> tags = tagsService.getTags().stream().map(tagsMapper::toDto).toList();
        return new ModelAndView("tags-list", Map.of("tags", tags));
    }

    @GetMapping("/{id}/delete")
    public ModelAndView delete(@PathVariable("id") int id) {
        boolean success = tagsService.deleteTag(id);
        return success ? new ModelAndView("redirect:/tags/list") : error("Cannot delete tag %d".formatted(id));
    }

    @PostMapping("/add")
    public ModelAndView save(@ModelAttribute("tagCreateDto") TagCreateDto tagCreateDto) {
        boolean success = tagsService.createTag(tagsMapper.fromDto(tagCreateDto));
        return success ? new ModelAndView("redirect:/tags/list") : error("Cannot create tag %s".formatted(tagCreateDto.getName()));
    }

    private ModelAndView error(String message) {
        return new ModelAndView("error-info", Map.of("message", message));
    }


}
