package com.words.match.db;

import com.words.match.exceptions.LinkAlreadyPresentException;
import com.words.match.exceptions.WordNotPresentException;
import com.words.match.model.Link;
import com.words.match.model.Word;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.words.match.exceptions.ExceptionController.throwLinkAlreadyPresentException;
import static com.words.match.exceptions.ExceptionController.throwWordNotPresentException;
import static com.words.match.model.Link.*;

@Component
public class LinkController {

    public static final String table_links = "links";

    private final JdbcTemplate jdbcTemplate;

    private final WordController wordController;

    public LinkController(@NonNull final JdbcTemplate jdbcTemplate, WordController wordController) {
        this.jdbcTemplate = jdbcTemplate;
        this.wordController = wordController;
    }

    public List<Link> findAll() {
        return null;
    }

    public List<Link> findLinks() {
        List<Link> links = new ArrayList<>();
        Object[] objects = jdbcTemplate.query(
                String.format("SELECT %s, %s, %s FROM %s", link_field_id, link_field_id1, link_field_id2, table_links), new Object[]{},
                (rs, rowNum) -> links.add(new Link(rs.getLong(link_field_id), rs.getLong(link_field_id1), rs.getLong(link_field_id2)))
        ).toArray();
        return links;
    }

    public List<Link> findLinks(@NonNull final long id) {
        List<Link> links = new ArrayList<>();
        Object[] objects = jdbcTemplate.query(
                String.format("SELECT distinct %s, %s, %s FROM %s WHERE %s = ? OR %s = ? group by %s, %s", link_field_id, link_field_id1, link_field_id2, table_links, link_field_id1, link_field_id2, link_field_id1, link_field_id2), new Object[]{id, id},
                (rs, rowNum) -> links.add(new Link(rs.getLong(link_field_id), rs.getLong(link_field_id1), rs.getLong(link_field_id2)))
        ).toArray();
        return links;
    }


    public Link find(final long id1, final long id2) {
        return findAll().stream()
                .filter(t -> (t.getId1() == id1 && t.getId2() == id2) || (t.getId2() == id1 && t.getId1() == id2))
                .findFirst().orElse(null);
    }

    public Link add(@NonNull final Link link) {
        if (!isPresent(link)) {
            return batchUpdate(link.getId1(), link.getId2());
        }
        return null;
    }

//    public void add(@NonNull final List<Link> links) {
//        links.forEach(this::add);
//    }

//    private List<Link> findLinks(@NonNull final long id) {
//        List<Link> links = new ArrayList<>();
//        Object[] objects = jdbcTemplate.query(
//                String.format("SELECT %s, %s, %s FROM %s WHERE %s = ? OR %s = ?", link_field_id, link_field_id1, link_field_id2, table_links, link_field_id1, link_field_id2), new Object[]{id, id},
//                (rs, rowNum) -> links.add(new Link(rs.getLong(link_field_id1), rs.getInt(link_field_id1), rs.getInt(link_field_id2)))).toArray();
//        return links;
//    }

    private Link batchUpdate(@NonNull final long id1, @NonNull  final long id2) {
        jdbcTemplate.batchUpdate(String.format("INSERT INTO %s(%s, %s) VALUES (?, ?)", table_links, link_field_id1, link_field_id2), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setLong(1, id1);
                preparedStatement.setLong(2, id2);
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
        return find(id1, id2);
    }

    private boolean isPresent(@NonNull final long id1, @NonNull final long id2) {
        return find(id1, id2) != null;
    }

    private boolean isPresent(@NonNull final Link id) {
        return isPresent(id.getId1(), id.getId2());
    }

    public Link link(@NonNull final String word1,
                     @NonNull final String word2) throws WordNotPresentException, LinkAlreadyPresentException {
        Word obj1 = wordController.find(word1);
        if (obj1 == null)
            throwWordNotPresentException(word1);

        Word obj2 = wordController.find(word2);
        if (obj2 == null)
            throwWordNotPresentException(word2);

        Link link = find(obj1.getId(), obj2.getId());
        if (link != null)
            throwLinkAlreadyPresentException(word1, word2);

        long id1 = obj1.getId();
        long id2 = obj2.getId();

        if (!isPresent(id1, id2)) {
            return batchUpdate(id1, id2);
        }
        return null;
    }

    private Word addIfNull(@NonNull final String word) {
        return null;
    }

    public Link link(long id1, long id2) {
        Word word = wordController.find(id1);
        Word word2 = wordController.find(id2);
        Link link = find(word.getId(), word2.getId());
        return add(link);

    }
}
