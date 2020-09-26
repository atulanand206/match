package com.words.para.entity;

public enum Content {
  CHARACTER(30),
  DIALOGUE(20),
  SCENE(10),
  INFORMATION(10),
  SPACE(0),
  SPEECH(0),
  FEELING(25),
  ADDITIONAL(0);
  private final int padding;
  private final int length;

  Content(final int padding) {
    this.padding = padding;
    this.length = 80 - 2 * padding;
  }

  public int getPadding() {
    return padding;
  }

  public int getLength() {
    return length;
  }
}
