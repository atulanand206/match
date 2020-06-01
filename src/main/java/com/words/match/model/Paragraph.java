package com.words.match.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.words.match.lang.LangDeserializer;
import com.words.match.lang.Language;

public class Paragraph {

    @JsonDeserialize(using = LangDeserializer.class)
    private Language language;

    private String paragraph;

    public Paragraph(Language language, String paragraph) {
        this.language = language;
        this.paragraph = paragraph;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}
