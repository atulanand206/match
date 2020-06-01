package com.words.match.db;

import com.words.match.model.Board;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.words.match.model.Board.board_field_board;
import static com.words.match.model.Board.board_field_id;
import static com.words.match.model.Board.board_field_name;

@Component
public class BoardController {

    public static final String table_board = "boards";

    private final JdbcTemplate jdbcTemplate;

    public BoardController(@NonNull final JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Board> findAll() {
        String sql = "SELECT * FROM "+ table_board;
        List<Board> boards = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Board.class));
        return boards;
    }

    public Board find(final long id) {
        AtomicReference<Board> result = new AtomicReference<>();
        findAll().forEach(word -> {
            if (id == word.getId())
                result.set(word);
        });
        return result.get();
    }

    public void add(@NonNull final Board word) {
        if (word.getId()==-1 || !isPresent(word.getId())) {
            batchUpdate(word);
        }
    }

    private boolean isPresent(@NonNull final long word) {
        List<Board> words = findWords(word);
        if (!words.isEmpty())
            return words.get(0).getBoard().equals(word);
        return false;
    }

    private List<Board> findWords(@NonNull final long word) {
        List<Board> words = new ArrayList<>();
        Object[] objects = jdbcTemplate.query(
                String.format("SELECT %s, %s, %s FROM %s WHERE %s = ?", board_field_id, board_field_name, board_field_board, table_board, board_field_id), new Object[]{word},
                (rs, rowNum) -> words.add(new Board(rs.getLong(board_field_id), rs.getString(board_field_name), rs.getString(board_field_board)))
        ).toArray();
        return words;
    }

    private boolean isPresent(@NonNull final Board word) {
        return isPresent(word.getId());
    }


    public void batchUpdate(@NonNull final Board board) {
        jdbcTemplate.batchUpdate(String.format("INSERT INTO %s(%s, %s) VALUES (?, ?)", table_board, board_field_name,  board_field_board), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setString(1, board.getName());
                preparedStatement.setString(2, board.getBoard());
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
    }

    public void put(@NonNull final Board board) {
        jdbcTemplate.batchUpdate(String.format("INSERT INTO %s(%s, %s, %s) VALUES (?, ?, ?)", table_board, board_field_id, board_field_name, board_field_board), new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setLong(1, board.getId());
                preparedStatement.setString(2, board.getName());
                preparedStatement.setString(3, board.getBoard());
            }

            @Override
            public int getBatchSize() {
                return 1;
            }
        });
    }

}
