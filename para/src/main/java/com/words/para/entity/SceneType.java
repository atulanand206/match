package com.words.para.entity;

public enum SceneType {
  INT("I", "INT."),
  EXT("E", "EXT."),
  INT_EXT("IE", "INT/EXT.");
  private final String type;
  private final String value;

  SceneType(final String type, final String value) {
    this.type = type;
    this.value = value;
  }

  public static SceneType getType(final String type) throws Exception {
    for (SceneType value : SceneType.values()) {
      if (value.type.equals(type))
        return value;
    }
    throw new Exception("Scene type does not exist");
  }

  public String getValue() {
    return value;
  }
}
