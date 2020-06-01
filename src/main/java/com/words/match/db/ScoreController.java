package com.words.match.db;

import com.words.match.model.Board;
import com.words.match.model.Score;
import com.words.match.model.ScoreResponse;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.words.match.model.Score.*;

@Component
public class ScoreController {

    public static final String table_score = "scores";

    private final JdbcTemplate jdbcTemplate;

    public ScoreController(@NonNull final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<ScoreResponse> findAll() {
        String sql = "SELECT * FROM "+ table_score + " ORDER BY score DESC";
        List<ScoreResponse> scores = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ScoreResponse.class));
        return scores;
    }

    public void add(@NonNull final Score word) {
//        if (word.getId()==-1 || !isPresent(word.getId())) {
            batchUpdate(word);
//        }
    }

    private boolean isPresent(@NonNull final long word) {
        List<ScoreResponse> words = findWords(word);
        return !words.isEmpty() && words.get(0).getId() == (word);
    }

    public List<ScoreResponse> findWords(@NonNull final long id) {
        List<ScoreResponse> scores = new ArrayList<>();
        Object[] objects = jdbcTemplate.query(
                "SELECT sc.id, sc.name, sc.board, sc.score, bo.name FROM Scores sc, Boards bo WHERE sc.board = bo.id AND sc.id = ? ORDER BY sc.score DESC", new Object[]{id},
//                String.format("SELECT %s, %s, %s, %s FROM %s WHERE %s = ?", score_field_id, score_field_name, score_field_board, score_field_score, table_score, score_field_id), new Object[]{id},
                (rs, rowNum) -> scores.add(new ScoreResponse(rs.getLong("id"), rs.getString("name"), rs.getLong("board"), rs.getInt("score"), rs.getString("boardname")))
        ).toArray();
        return scores;
    }

    private boolean isPresent(@NonNull final Score word) {
        return isPresent(word.getId());
    }


    public void batchUpdate(@NonNull final Score score) {
        List<Board> list = jdbcTemplate.query("SELECT * from boards where id = ?", new Object[]{score.getBoard()}, new BeanPropertyRowMapper<>(Board.class));
        if (list.isEmpty())
            return;
        jdbcTemplate.batchUpdate(String.format("INSERT INTO %s(%s, %s, %s, %s) VALUES (?, ?, ?, ?)", table_score, score_field_name, score_field_board,  score_field_score, score_field_board_name), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, score.getName());
                preparedStatement.setLong(2, score.getBoard());
                preparedStatement.setInt(3, score.getScore());
                preparedStatement.setString(4, list.get(0).getName());
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
    }

}
