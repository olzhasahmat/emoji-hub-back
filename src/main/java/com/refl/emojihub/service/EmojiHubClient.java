package com.refl.emojihub.service;

import com.refl.emojihub.dto.EmojiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EmojiHubClient {

    private final WebClient webClient;

    public List<EmojiDto> getAllEmojis() {
        return webClient.get()
                .uri("https://emojihub.yurace.pro/api/all")
                .retrieve()
                .bodyToFlux(EmojiDto.class)
                .collectList()
                .block();
    }
}
