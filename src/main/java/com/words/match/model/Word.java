package com.words.match.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.words.match.lang.Language;
import com.words.match.lang.LangDeserializer;
import org.springframework.lang.NonNull;

import static com.words.match.lang.Language.English;

public class Word {

    public static final String word_field_id = "id";
    public static final String word_field_word = "word";
    public static final String word_field_language = "language";
    public static final String word_field_meaning = "meaning";
    public static final String word_field_speech = "speech";

    private long id;

    private String word;

    private String meaning;

    private String speech;

    @JsonDeserialize(using = LangDeserializer.class)
    private Language language;

    public Word() {
    }

    public Word(@NonNull final String word,
                @NonNull final Language language) {
        this.word = word;
        this.language = language;
    }

    public Word(final long id,
                @NonNull final String word) {
        this.id = id;
        this.word = word;
        this.language = English;
    }

    public Word(final long id,
                @NonNull final String word,
                @NonNull final Language language) {
        this.id = id;
        this.word = word;
        this.language = language;
    }

    public Word(long id, String word, Language language, String speech, String meaning) {
        this.id = id;
        this.word = word;
        this.meaning = meaning;
        this.speech = speech;
        this.language = language;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @NonNull
    public String getWord() {
        return word;
    }

    public void setWord(@NonNull String word) {
        this.word = word;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(@NonNull final Language language) {
        this.language = language;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSpeech() {
        return speech;
    }

    public void setSpeech(String speech) {
        this.speech = speech;
    }

    @Override
    public String toString() {
        return word_field_word + "{" +
                word_field_id + "=" + id + ", " +
                word_field_word + "='" + word + '\'' + ", " +
                word_field_language + "='" + language + '\'' +
                '}';
    }
}
