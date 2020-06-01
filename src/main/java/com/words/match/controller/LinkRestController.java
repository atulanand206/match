package com.words.match.controller;

import com.words.match.db.LinkController;
import com.words.match.exceptions.WordNotPresentException;
import com.words.match.model.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.words.match.model.Link.*;
import static com.words.match.model.Word.word_field_word;

@RestController
public class LinkRestController {

    @NonNull
    private final LinkController dbController;

    public LinkRestController(@NonNull final LinkController dbController) {
        this.dbController = dbController;
    }

    @PostMapping(path = "/link")
    public ResponseEntity post(@RequestBody Link link) {
        dbController.add(link);
        return ResponseEntity.ok(HttpStatus.OK + " " + link);
    }

    @PostMapping(path = "/link/pair")
    public ResponseEntity post(@RequestParam(value = link_param_word1) String word1, @RequestParam(value = link_param_word2) String word2) {
        Link link;
        try {
            link = dbController.link(word1, word2);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok(HttpStatus.OK + " " + link);
    }

    @PostMapping(path = "/link/ids")
    public ResponseEntity post(@RequestParam(value = link_field_id1) long id1, @RequestParam(value = link_field_id2) long id2) {
        Link link = null;
        Link result = dbController.link(id1, id2);
        return ResponseEntity.ok(HttpStatus.OK + " " + result);
    }

//    @GetMapping(path = "/link")
//    public List<Link> get(@RequestParam(value = word_field_word, defaultValue = "World") long link) {
//        return dbController.find(link);
//    }
//

    @GetMapping(path = "/link/item")
    public List<Link> getAll(@RequestParam(value = link_field_id) long id) {
        return dbController.findLinks(id);
    }

    @GetMapping(path = "/links")
    public List<Link> getAll() {
        return dbController.findAll();
    }
//
//    @PostMapping(path = "/links")
//    public ResponseEntity post(@RequestBody List<Link> links) {
//        dbController.add(links);
//        return ResponseEntity.ok(HttpStatus.OK);
//    }
}
