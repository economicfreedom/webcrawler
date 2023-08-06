package com.test2.webcrawler.crwaling.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class NewsTitleVO {
    private final String newsTitle;
    private final LocalDateTime searchTime;

}
