package com.words.match.model;

import com.words.match.lang.Language;

import static com.words.match.db.LinkController.table_links;

public class Link {

    public static final String link_field_id = "id";
    public static final String link_field_id1 = "id1";
    public static final String link_field_id2 = "id2";
    public static final String link_param_word1 = "word1";
    public static final String link_param_word2 = "word2";

    private long id;

    private long id1;

    private long id2;

    public Link() {
    }

    public Link(long id, long id1, long id2) {
        this.id = id;
        this.id1 = id1;
        this.id2 = id2;
    }

    public long getId() {
        return id;
    }

    public long getId1() {
        return id1;
    }

    public long getId2() {
        return id2;
    }

    @Override
    public String toString() {
        return table_links + "{" +
                link_field_id + "=" + id + ", " +
                link_field_id1 + "=" + id1 + ", " +
                link_field_id2 + "=" + id2 + ", " +
                '}';
    }
}
