package com.refl.emojihub.service;

import com.refl.emojihub.dto.EmojiDto;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmojiService {

    private final EmojiHubClient client;

    @Cacheable("emojis")
    public List<EmojiDto> getEmojis(
            String search,
            String category,
            String sort
    ) {
        Stream<EmojiDto> stream = client.getAllEmojis().stream();

        if (Strings.isNotBlank(search)) {
            stream = stream.filter(e ->
                    e.getName().toLowerCase().contains(search.toLowerCase()));
        }

        if (category != null) {
            stream = stream.filter(e ->
                    e.getCategory().equalsIgnoreCase(category));
        }

        if ("asc".equals(sort)) {
            stream = stream.sorted(Comparator.comparing(EmojiDto::getName));
        }

        List<EmojiDto> emojis = stream.toList();
        emojis.forEach(e -> {
            if (e.getId() == null) {
                e.setId(
                        UUID.nameUUIDFromBytes(
                                e.getName().getBytes()
                        ).toString()
                );
            }
        });
        return emojis;
    }
}
