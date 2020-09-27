package com.words.para.entity.dto;

import java.util.List;

public class SpeechDto extends LineDto {

  private String character;

  private String dialogue;

  private String feeling;

  private List<String> additionals;

  public SpeechDto() {
    super(LineType.Speech);
  }

  public String getCharacter() {
    return character;
  }

  public String getDialogue() {
    return dialogue;
  }

  public String getFeeling() {
    return feeling;
  }

  public List<String> getAdditionals() {
    return additionals;
  }
}
