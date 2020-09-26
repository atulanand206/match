package com.words.para.entity;

import java.util.ArrayList;
import java.util.List;

public class Script {

  private final List<Scene> fScenes = new ArrayList<>();

  public Script() { }

  public void printLines() {
    fScenes.forEach(Scene::printLines);
  }

  public List<Scene> getScenes() {
    return fScenes;
  }

  public void addScene(final Scene scene) {
    getScenes().add(scene);
  }
}