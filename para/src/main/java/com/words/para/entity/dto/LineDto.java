package com.words.para.entity.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.EXISTING_PROPERTY,
    property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(
        value = SpeechDto.class,
        name = LineType.Type_Speech),
    @JsonSubTypes.Type(
        value = InformationDto.class,
        name = LineType.Type_Info)
})
public class LineDto {

  private LineType type;

  public LineDto(final LineType type) {
    this.type = type;
  }

  public LineType getType() {
    return type;
  }

  public void setType(final LineType type) {
    this.type = type;
  }
}
