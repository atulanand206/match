package com.words.para.entity.content;

import com.words.para.entity.Content;
import com.words.para.entity.Line;

public class Character extends Line {

  public Character(final String text) {
    super(Content.CHARACTER, text);
  }

  public static String dialogueContinuedString() {
    return " (CONTINUED)";
  }
}
