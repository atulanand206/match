package com.words.para.entity.content;

import com.words.para.entity.Line;
import com.words.para.entity.SceneType;
import static com.words.para.entity.Content.SCENE;

public class Header extends Line {

  private int fNumber;
  private SceneType fSceneType;
  private String fTime;

  public Header(
      final int number,
      final SceneType sceneType,
      final String text,
      final String time) {
    super(SCENE, text);
    this.fNumber = number;
    this.fSceneType = sceneType;
    this.fTime = time;
  }

  @Override
  public String generateText() {
    StringBuilder text = new StringBuilder(fNumber + spaces(padding() - 1));
    text.append(fSceneType.getValue());
    text.append(" ");
    text.append(getText().toUpperCase());
    text.append(" - ");
    text.append(fTime);
    int length = text.length();
    text.append(" ".repeat(Math.max(0, 80 - length - padding())));
    text.append(fNumber);
    text.append("\n");
    return text.toString();
  }

}
