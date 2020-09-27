package com.words.para.entity.dto;

import java.util.List;

public class ScriptDto {

  private List<SceneDto> scenes;

  public List<SceneDto> getScenes() {
    return scenes;
  }

  public static String getScript(final ScriptDto scriptDto) throws Exception {
    StringBuilder script = new StringBuilder();
    for (SceneDto sceneDto : scriptDto.getScenes()) {
      script.append(SceneDto.getScene(sceneDto));
    }
    return script.toString();
  }
}
