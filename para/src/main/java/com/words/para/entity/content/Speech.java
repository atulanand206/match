package com.words.para.entity.content;

import com.words.para.entity.Content;
import com.words.para.entity.Line;
import java.util.ArrayList;
import java.util.List;

public class Speech extends Line {

  private final Line fCharacter;
  private final Line fFeeling;
  private final List<Additional> fAdditionals = new ArrayList<>();

  public Speech(final Line character, final Line dialogue, final Line feeling) {
    super(Content.SPEECH);
    fCharacter = character;
    fFeeling = feeling;
    fAdditionals.add(new Additional(dialogue));
  }

  public Speech(final Line character, final Line feeling, final List<Additional> additionals) {
    super(Content.SPEECH);
    fCharacter = character;
    fFeeling = feeling;
    fAdditionals.addAll(additionals);
  }

  @Override
  public void print() {
    fCharacter.print();
    fFeeling.print();
    fAdditionals.forEach(Additional::print);
  }
}
