package com.words.para.entity.content;

import com.words.para.entity.Content;
import com.words.para.entity.Line;

public class Additional extends Line {

  private final Line fDialogue;
  private final Line fFeeling;

  public Additional(final Line dialogue) {
    super(Content.ADDITIONAL);
    fDialogue = dialogue;
    fFeeling = new Feeling("");
  }

  public Additional(final Line dialogue, final Line feeling) {
    super(Content.ADDITIONAL);
    fDialogue = dialogue;
    fFeeling = feeling;
  }

  @Override
  public void print() {
    fDialogue.print();
    fFeeling.print();
  }
}
