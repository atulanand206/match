package com.words.para.magic;

import com.words.para.entity.dto.SceneDto;
import com.words.para.entity.dto.ScriptDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.words.para.entity.dto.SceneDto.getScene;
import static com.words.para.entity.dto.ScriptDto.getScript;

@RestController
@RequestMapping("/movie")
public class ScriptController {

  @PostMapping(
      value = "/scene",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String convertScene(
      @RequestBody final SceneDto sceneDto) throws Exception {
    return getScene(sceneDto);
  }

  @PostMapping(
      value = "/script",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public String convertScript(
      @RequestBody final ScriptDto scriptDto) throws Exception {
    return getScript(scriptDto);
  }
}
