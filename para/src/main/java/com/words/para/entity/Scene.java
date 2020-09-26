package com.words.para.entity;

import com.words.para.entity.content.Additional;
import com.words.para.entity.content.Character;
import com.words.para.entity.content.Dialogue;
import com.words.para.entity.content.Feeling;
import com.words.para.entity.content.Header;
import com.words.para.entity.content.Information;
import com.words.para.entity.content.Space;
import com.words.para.entity.content.Speech;
import java.util.ArrayList;
import java.util.List;
import static com.words.para.entity.content.Character.dialogueContinuedString;
import static com.words.para.entity.content.Dialogue.additionalSplitString;

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

  public void addDialogue(final String characterName, final String dialogue, final String feeling) {
    addLine(new Speech(getCharacter(characterName), new Dialogue(dialogue), new Feeling(feeling)));
    addSpace();
  }

  public void addDialogue(
      final String characterName,
      final String dialogues,
      final String feelingString,
      final String... additionalStrings) {
    addLine(new Speech(getCharacter(characterName), new Feeling(feelingString),
        getAdditionals(dialogues, additionalStrings)));
    addSpace();
  }

  private List<Additional> getAdditionals(
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

  public void addLine(Line line) {
    fLines.add(line);
  }

  public void addSpace() {
    addLine(new Space());
  }

  public void printLines() {
    for (Line line : fLines) {
      line.print();
    }
  }

}
