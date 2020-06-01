package com.words.match.lang;

import org.springframework.lang.NonNull;

public enum Language {

    English(0, "English"),
    Spanish(1, "Spanish"),
    Italian(2, "Italian"),
    French(3, "French"),
    German(4, "German"),
    Indian(5, "Indian"),
    Latin(6, "Latin");

    private long index;
    private String language;

    Language(final long index, @NonNull final String language) {
        this.index = index;
        this.language = language;
    }

    public long getIndex() {
        return index;
    }

    public String getLanguage() {
        return language;
    }

    public static Language indexOf(long index) {
        for (Language language : Language.values())
            if (language.getIndex() == index)
                return language;
        return English;
    }
}
