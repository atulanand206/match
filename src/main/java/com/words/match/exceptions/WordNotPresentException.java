package com.words.match.exceptions;

import java.io.IOException;

public class WordNotPresentException extends IOException {

    public WordNotPresentException(String word) {
        super(String.format("%s is not present in the database, please add it and try again.", word));
    }
}
