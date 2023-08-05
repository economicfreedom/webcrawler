package com.test2.webcrawler.crwaling.model;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class NewsTitleVO {
    private final Integer id;
    private final String site;
    private final String newsTitle;
    private final String searchTime;

}
