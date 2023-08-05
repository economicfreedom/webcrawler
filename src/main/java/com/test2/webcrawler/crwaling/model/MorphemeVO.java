package com.test2.webcrawler.crwaling.model;

import lombok.*;

@Getter
@ToString
@RequiredArgsConstructor
public class MorphemeVO implements ResponseFactory {
    private final String sub;
    private final String category;

}
