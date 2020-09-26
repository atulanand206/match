package com.words.para.entity.content;

import com.words.para.entity.Content;
import com.words.para.entity.Line;

public class Additional extends Line {

  private final Line fDialogue;
  private final Line fFeeling;

  public Additional(final Line dialogue, final Line feeling) {
    super(Content.ADDITIONAL);
    fDialogue = dialogue;
    fFeeling = feeling;
  }

  @Override
  public String generateText() {
    return fDialogue.generateText() + fFeeling.generateText();
  }
}
