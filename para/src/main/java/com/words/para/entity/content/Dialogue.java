package com.words.para.entity.content;

import com.words.para.entity.Content;
import com.words.para.entity.Line;

public class Dialogue extends Line {

  public Dialogue(final String text) {
    super(Content.DIALOGUE, text);
  }

  public static String additionalSplitString() {
    return "-----";
  }
}
