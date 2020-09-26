package com.words.para.entity.content;

import com.words.para.entity.Content;
import com.words.para.entity.Line;

public class Feeling extends Line {

  public Feeling(final String text) {
    super(Content.FEELING, text.isEmpty() ? "" : String.format("(%s)",text));
  }

  @Override
  public String generateText() {
    if (!getText().isEmpty()) {
      return super.generateText();
    }
    return "";
  }
}
