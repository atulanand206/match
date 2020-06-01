package com.words.match.model;

public class ScoreResponse extends Score {

    private String boardname;

    public ScoreResponse() {
    }

    public ScoreResponse(long id, String name, long board, int score, String boardname) {
        super(id, name, board, score);
        this.boardname = boardname;
    }

    public String getBoardname() {
        return boardname;
    }

    public void setBoardname(String boardname) {
        this.boardname = boardname;
    }
}
