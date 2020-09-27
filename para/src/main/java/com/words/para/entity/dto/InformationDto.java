package com.words.para.entity.dto;

public class InformationDto extends LineDto {

  private String information;

  public InformationDto() {
    super(LineType.Info);
  }

  public String getInformation() {
    return information;
  }
}
