package com.words.match.db;

import com.words.match.lang.Language;
import com.words.match.model.Paragraph;
import com.words.match.model.Word;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.words.match.model.Word.*;

@Component
public class WordController {

    public static final String table_words = "words";

    private final JdbcTemplate jdbcTemplate;

    private List<String> strings = new ArrayList<>();

    public WordController(@NonNull final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Word> findAll() {
        String sql = "SELECT * FROM "+ table_words;
        List<Word> word = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Word.class));
        return word;
    }

    @Nullable
    public Word find(@NonNull final String word) {
        if (isPresent(word)) {
            List<Word> words = findWords(word);
            return words.get(0);
        }
        return null;
    }

    public Word find(final long id) {
        AtomicReference<Word> result = new AtomicReference<>();
        findAll().forEach(word -> {
            if (id == word.getId())
                result.set(word);
        });
        return result.get();
    }

    public void add(@NonNull final Word word) {
        if (!isPresent(word)) {
            batchUpdate(word);
        }
    }


    public void add(Paragraph paragraph) {
        String[] split = paragraph.getParagraph().split("[ .,;:'\"()*&^%$#@!~`|<>/]");
        for (String str : split)
            add(new Word(str, paragraph.getLanguage()));
    }

    public void add(@NonNull final List<Word> words) {
        words.forEach(this::add);
    }


    private List<Word> findWords(@NonNull final String word) {
        List<Word> words = new ArrayList<>();
        Object[] objects = jdbcTemplate.query(
                String.format("SELECT %s, %s, %s, %s, %s FROM %s WHERE %s = ?", word_field_id, word_field_word, word_field_language, word_field_meaning, word_field_speech, table_words, word_field_word), new Object[]{word},
                (rs, rowNum) -> words.add(new Word(rs.getLong(word_field_id), rs.getString(word_field_word), Language.valueOf(rs.getString(word_field_language)), rs.getString(word_field_meaning), rs.getString(word_field_speech)))
        ).toArray();
        return words;
    }

    private void batchUpdate(@NonNull final Word word) {
        jdbcTemplate.batchUpdate(String.format("INSERT INTO %s(%s, %s, %s, %s) VALUES (?, ?, ?, ?)", table_words, word_field_word, word_field_language, word_field_meaning, word_field_speech), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, word.getWord());
                preparedStatement.setString(2, word.getLanguage().getLanguage());
                preparedStatement.setString(3, word.getMeaning());
                preparedStatement.setString(4, word.getSpeech());
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
    }

    private void batchUpdate(@NonNull final List<Word> words) {
        jdbcTemplate.batchUpdate(String.format("INSERT INTO %s(%s, %s, %s, %s) VALUES (?, ?, ?, ?)", table_words, word_field_word, word_field_language, word_field_meaning, word_field_speech), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                Word word = words.get(i);
                preparedStatement.setString(1, word.getWord());
                preparedStatement.setString(2, word.getLanguage().getLanguage());
                preparedStatement.setString(3, word.getMeaning());
                preparedStatement.setString(4, word.getSpeech());
            }

            @Override
            public int getBatchSize() {
                return words.size();
            }
        });
    }

    private boolean isPresent(@NonNull final String word) {
        List<Word> words = findWords(word);
        if (!words.isEmpty())
            return words.get(0).getWord().equals(word);
        return false;
    }

    private boolean isPresent(@NonNull final Word word) {
        return isPresent(word.getWord());
    }

    public Word isValid(String word) {
        word = word.toLowerCase();
        List<Word> words = findWords(word);
        if (words.isEmpty())
            return null;
        else
            return words.get(0);
    }
}
