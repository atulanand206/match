package com.words.match.exceptions;

public class LinkAlreadyPresentException extends Exception {

    public LinkAlreadyPresentException(String word1, String word2) {
        super(String.format("The link of item %s & %s is already present in the database", word1, word2));
    }
}
