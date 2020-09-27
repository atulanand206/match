package com.words.para.entity.dto;

import com.words.para.entity.Scene;
import com.words.para.entity.SceneType;
import java.util.List;

public class SceneDto {

  private int number;

  private String type;

  private String name;

  private String time;

  private List<LineDto> lines;

  public int getNumber() {
    return number;
  }

  public String getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public String getTime() {
    return time;
  }

  public List<LineDto> getLines() {
    return lines;
  }

  public static String getScene(final SceneDto sceneDto) throws Exception {
    Scene scene =
        new Scene(sceneDto.getNumber(), SceneType.getType(sceneDto.getType()), sceneDto.getName(),
            sceneDto.getTime());
    for (LineDto lineDto : sceneDto.getLines()) {
      if (lineDto instanceof InformationDto) {
        scene.addInfo(((InformationDto) lineDto).getInformation());
      } else if (lineDto instanceof SpeechDto) {
        SpeechDto speechDto = (SpeechDto) lineDto;
        List<String> additionals = speechDto.getAdditionals();
        if (additionals == null) {
          if (speechDto.getFeeling() != null) {
            scene.addDialogue(speechDto.getCharacter(), speechDto.getDialogue(),
                speechDto.getFeeling());
          } else {
            scene.addDialogue(speechDto.getCharacter(), speechDto.getDialogue());
          }
        } else {
          String[] strings = new String[additionals.size()];
          for (int i = 0; i < strings.length; i++) {
            strings[i] = additionals.get(i);
          }
          if (speechDto.getFeeling() != null) {
            scene.addDialogue(speechDto.getCharacter(), speechDto.getDialogue(),
                speechDto.getFeeling(), strings);
          } else {
            scene.addDialogue(speechDto.getCharacter(), speechDto.getDialogue(),
                "", strings);
          }
        }
      }
    }
    return scene.generate();
  }
}
