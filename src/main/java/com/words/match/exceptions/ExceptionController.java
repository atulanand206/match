package com.words.match.exceptions;

import org.springframework.stereotype.Component;

@Component
public class ExceptionController {
    public static void throwWordNotPresentException(String word) throws WordNotPresentException {
        throw new WordNotPresentException(word);
    }

    public static void throwLinkAlreadyPresentException(String word1, String word2) throws LinkAlreadyPresentException {
        throw new LinkAlreadyPresentException(word1, word2);
    }
}
