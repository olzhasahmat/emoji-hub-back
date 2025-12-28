package com.refl.emojihub.service;

import org.springframework.stereotype.Service;

@Service
public class LlmService {
    public String generateAtmosphere(String emojiName) {
        return "This emoji represents a warm and positive atmosphere around " + emojiName;
    }
}
