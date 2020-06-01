package com.words.match.controller;

import com.words.match.db.BoardController;
import com.words.match.db.ScoreController;
import com.words.match.db.TableController;
import com.words.match.db.WordController;
import com.words.match.model.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.words.match.model.Board.board_field_id;
import static com.words.match.model.Word.word_field_word;

@RestController
public class WordRestController {

    @NonNull final WordController dbController;

    @NonNull final TableController tableController;

    @NonNull final ScoreController scoreController;

    @NonNull final BoardController boardController;
    public WordRestController(@NonNull final WordController dbController,
                              @NonNull final TableController tableController,
                              @NonNull final ScoreController scoreController,
                              @NonNull final BoardController boardController) {
        this.dbController = dbController;
        this.tableController = tableController;
        this.scoreController = scoreController;
        this.boardController = boardController;
    }

    @GetMapping(path = "/word")
    public Word get(@RequestParam(value = word_field_word, defaultValue = "World") String word) {
        return dbController.find(word);
    }

    @PostMapping(path = "/word")
    public ResponseEntity post(@RequestBody Word word) {
        dbController.add(word);
        return ResponseEntity.ok(HttpStatus.OK + " " + word);
    }

    @PostMapping(path = "/words/paragraph")
    public ResponseEntity post(@RequestBody Paragraph paragraph) {
        dbController.add(paragraph);
        return ResponseEntity.ok(HttpStatus.OK + " " + paragraph);
    }

    @GetMapping(path = "/words")
    public List<Word> getAll() {
        return dbController.findAll();
    }

    @PostMapping(path = "/words")
    public ResponseEntity post(@RequestBody List<Word> words) {
        dbController.add(words);
        return ResponseEntity.ok(HttpStatus.OK + " " + words.size());
    }

    @GetMapping(path = "/words/valid")
    public ResponseEntity isValid(@RequestParam String word) {
        Word valid = dbController.isValid(word);
        if (valid == null)
            return ResponseEntity.badRequest().body(word + " not present");
        return ResponseEntity.ok(valid);
    }

    @PostMapping(path = "/words/clear")
    public ResponseEntity clear() {
        tableController.deleteAndCreateWordTable();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(path = "/words/count")
    public int count() {
        return dbController.findAll().size();
    }

    @GetMapping(path = "/boards/board")
    public Board getBoard(@RequestParam(value = board_field_id) int index) {
        return boardController.find(index);
    }

    @PostMapping(path = "/boards/board")
    public void addBoard(@RequestBody final Board board) {
//        board.setBoard(board.getBoard().replace("\"", "\\\""));
        boardController.add(board);
    }

    @PutMapping(path = "/boards/board")
    public ResponseEntity updateBoard(@RequestBody Board board) {
        board.setBoard(board.getBoard().replace("\"", "\\\""));
        boardController.put(board);
        return ResponseEntity.ok(board);
    }

    @GetMapping(path = "/boards")
    public List<Board> boards() {
        return boardController.findAll();
    }

    @PostMapping(path = "/board/clear")
    public ResponseEntity clearBoard() {
        tableController.deleteAndCreateBoardTable();
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping(path = "/scores/game")
    public ResponseEntity addGame(@RequestBody Score score){
        scoreController.add(score);
        return ResponseEntity.ok(score);
    }

    @GetMapping(path = "/scores")
    public List<ScoreResponse> findScores() {
        return scoreController.findAll();
    }

    @GetMapping(path = "/scores/board")
    public ScoreResponse findScore(@RequestParam long id) {
        List<ScoreResponse> words = scoreController.findWords(id);
        if (words.isEmpty())
            return null;
        return words.get(0);

    }

    @PostMapping(path = "/scores/clear")
    public ResponseEntity clearScores() {
        tableController.deleteAndCreateScoreTable();
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
