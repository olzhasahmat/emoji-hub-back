package com.refl.emojihub.controller;

import com.refl.emojihub.dto.EmojiDto;
import com.refl.emojihub.service.EmojiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/emojis")
@RequiredArgsConstructor
public class EmojiController {

    private final EmojiService service;

    @GetMapping
    public List<EmojiDto> getEmojis(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String sort
    ) {
        log.info("Search: " + search + ", Category: " + category + ", Sort: " + sort);
        return service.getEmojis(search, category, sort);
    }
}
