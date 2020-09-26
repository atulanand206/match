package com.words.para.entity;

import java.util.ArrayList;
import java.util.List;

public class Script {

  private final List<Scene> fScenes = new ArrayList<>();

  public Script() { }

  public String generate() {
    StringBuilder stringBuilder = new StringBuilder();
    fScenes.forEach(scene -> stringBuilder.append(scene.generate()));
    return stringBuilder.toString();
  }

  public List<Scene> getScenes() {
    return fScenes;
  }

  public void addScene(final Scene scene) {
    getScenes().add(scene);
  }
}