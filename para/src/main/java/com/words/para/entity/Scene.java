package com.words.para.entity;

import com.words.para.entity.content.Character;
import com.words.para.entity.content.Header;
import com.words.para.entity.content.Information;
import com.words.para.entity.content.Space;
import com.words.para.entity.content.Speech;
import java.util.ArrayList;
import java.util.List;
import static com.words.para.entity.content.Character.dialogueContinuedString;

public class Scene {

  private final List<Line> fLines = new ArrayList<Line>();
  private String lastCharacterName;

  public Scene(
      final int number,
      final SceneType sceneType,
      final String text,
      final String time) {
    addLine(new Header(number, sceneType, text, time));
    addSpace();
  }

  public void addInfo(final String info) {
    addLine(new Information(info));
    addSpace();
  }

  public void addDialogue(final String character, final String dialogue) {
    addDialogue(character, dialogue, "");
  }

  public void addDialogue(
      final String characterName,
      final String dialogue,
      final String feelingString,
      final String... additionalStrings) {
    addLine(new Speech(getCharacter(characterName), dialogue, feelingString, additionalStrings));
  }

  private Character getCharacter(final String characterName) {
    Character character;
    if (lastCharacterName != null && lastCharacterName.equals(characterName)) {
      character = new Character(characterName + dialogueContinuedString());
    } else {
      character = new Character(characterName);
    }
    lastCharacterName = characterName;
    return character;
  }

  private void addSpace() {
    addLine(new Space());
  }

  private void addLine(final Line line) {
    fLines.add(line);
  }

  public String generate() {
    StringBuilder stringBuilder = new StringBuilder();
    for (Line line : fLines)
      stringBuilder.append(line.generateText());
    return stringBuilder.toString();
  }

}
