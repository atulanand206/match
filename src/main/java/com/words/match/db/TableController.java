package com.words.match.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static com.words.match.db.BoardController.table_board;
import static com.words.match.db.LinkController.table_links;
import static com.words.match.db.ScoreController.table_score;
import static com.words.match.db.WordController.table_words;
import static com.words.match.model.Board.*;
import static com.words.match.model.Link.*;
import static com.words.match.model.Score.*;
import static com.words.match.model.Word.*;

@Component
public class TableController {

    private static final Logger log = LoggerFactory.getLogger(TableController.class);

    JdbcTemplate jdbcTemplate;

    public TableController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createWordTable();
        createLinkTable();
        createBoardTable();
        createScoreTable();
    }

    public void createScoreTable() {
        if (isTablePresent(table_score))
            return;

        deleteAndCreateScoreTable();
    }

    public void deleteAndCreateScoreTable() {
        jdbcTemplate.execute(String.format("DROP TABLE %s IF EXISTS", table_score));
        jdbcTemplate.execute(String.format("CREATE TABLE %s(%s SERIAL, %s BIGINT, %s VARCHAR(255), %s VARCHAR(255), %s BIGINT)", table_score, score_field_id, score_field_board, score_field_board_name, score_field_name, score_field_score));
    }
//, CONSTRAINT fk_score_board_id FOREIGN KEY (name) REFERENCES boards(id), CONSTRAINT fk_score_board_id FOREIGN KEY (name) REFERENCES boards(id)
    public void createLinkTable() {
        if (isTablePresent(table_links))
            return;

        jdbcTemplate.execute(String.format("DROP TABLE %s IF EXISTS", table_links));
        jdbcTemplate.execute(String.format("CREATE TABLE %s(%s SERIAL, %s BIGINT, %s BIGINT)", table_links, link_field_id, link_field_id1, link_field_id2));
    }

    public void createWordTable() {
        if (isTablePresent(table_words))
            return;

        deleteAndCreateWordTable();
    }

    public void deleteAndCreateWordTable() {
        jdbcTemplate.execute(String.format("DROP TABLE %s IF EXISTS", table_words));
        jdbcTemplate.execute(String.format("CREATE TABLE %s(%s SERIAL, %s VARCHAR(255), %s VARCHAR(255), %s VARCHAR(255), %s VARCHAR(255))", table_words, word_field_id, word_field_word, word_field_language, word_field_meaning, word_field_speech));
    }

    public void createBoardTable() {
        if (isTablePresent(table_board))
            return;

        deleteAndCreateBoardTable();
    }

    public void deleteAndCreateBoardTable() {
        jdbcTemplate.execute(String.format("DROP TABLE %s IF EXISTS", table_board));
        jdbcTemplate.execute(String.format("CREATE TABLE %s(%s SERIAL, %s VARCHAR(1000), %s VARCHAR(1000))", table_board, board_field_id, board_field_name, board_field_board));
    }


    private boolean isTablePresent(String table) {
        boolean tableExists = false;
        try {
            ResultSet tableNames = jdbcTemplate.getDataSource().getConnection().getMetaData().getTables(null,"%", table, null); // instead of types
            tableExists = true;
        } catch (SQLException e) {
            @SuppressWarnings("ThrowableNotThrown") SQLException throwables = new SQLException("DBHandler.isTableExisting - SQLEXception: " + " sqlState " + e.getSQLState() + " errorcode: " + e.getErrorCode());
        }
        return tableExists;
    }
}
