package com.words.para.entity;

public enum SceneType {
  INT("INT."),
  EXT("EXT."),
  INT_EXT("INT/EXT.");
  private String value;

  SceneType(final String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
