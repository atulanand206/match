package com.words.match.model;

import java.util.HashMap;
import java.util.Map;

public class Board {

    public static final String board_field_id = "id";
    public static final String board_field_name = "name";
    public static final String board_field_board = "board";

    private long id = -1;

    private String name;

    private String board;

    public Board() {
    }

    public Board(String board) {
        this.board = board;
    }

    public Board(long id, String board) {
        this.id = id;
        this.board = board;
    }

    public Board(long id, String name, String board) {
        this.id = id;
        this.name = name;
        this.board = board;
    }

    public String getBoard() {
        return board;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
