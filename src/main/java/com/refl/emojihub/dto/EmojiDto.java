package com.refl.emojihub.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmojiDto {
    private String id;
    private String name;
    private String category;
    private String group;
    private List<String> htmlCode;
    private List<String> unicode;
}
