package com.test2.webcrawler.crwaling.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
@RequiredArgsConstructor
public class RequestAndResponseVO implements ResponseFactory {

    private final String newsType;
    private final String request;
    private final String response;
    private final LocalDateTime createdAt;
    private final Integer cloudId;
}
