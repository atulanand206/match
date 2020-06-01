package com.words.match.model;

public class Score {

    public static final String score_field_id = "id";
    public static final String score_field_board = "board";
    public static final String score_field_board_name = "boardname";
    public static final String score_field_name = "name";
    public static final String score_field_score = "score";

    private long id = -1;

    private String name;
    private long board;
    private int score;

    public Score() {
    }

    public Score(String name, long board, int score) {
        this.name = name;
        this.board = board;
        this.score = score;
    }

    public Score(long id, String name, long board, int score) {
        this.id = id;
        this.name = name;
        this.board = board;
        this.score = score;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getBoard() {
        return board;
    }

    public void setBoard(long board) {
        this.board = board;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
