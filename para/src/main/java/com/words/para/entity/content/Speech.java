package com.words.para.entity.content;

import com.words.para.entity.Content;
import com.words.para.entity.Line;
import java.util.ArrayList;
import java.util.List;
import static com.words.para.entity.content.Dialogue.additionalSplitString;

public class Speech extends Line {

  private final Line fCharacter;
  private final Line fFeeling;
  private final List<Additional> fAdditionals = new ArrayList<>();

  public Speech(
      final Line character, final String dialogues, final String feeling,
      final String... additionalStrings) {
    super(Content.SPEECH);
    fCharacter = character;
    fFeeling = new Feeling(feeling);
    fAdditionals.addAll(getAdditionals(dialogues, additionalStrings));
  }

  public List<Additional> getAdditionals(
      final String dialogues,
      final String[] additionalStrings) {
    List<Additional> additionals = new ArrayList<>();
    String[] split = dialogues.split(additionalSplitString());
    for (int i = 0, splitLength = split.length; i < splitLength; i++) {
      String dialogue = split[i];
      String feeling = "";
      if (additionalStrings.length > i) {
        feeling = additionalStrings[i];
      }
      additionals.add(new Additional(new Dialogue(dialogue), new Feeling(feeling)));
    }
    return additionals;
  }

  @Override
  public String generateText() {
    StringBuilder stringBuilder = new StringBuilder(fCharacter.generateText().toUpperCase());
    stringBuilder.append(fFeeling.generateText());
    for (Additional fAdditional : fAdditionals) {
      stringBuilder.append(fAdditional.generateText());
    }
    return stringBuilder.toString() + "\n";
  }
}
