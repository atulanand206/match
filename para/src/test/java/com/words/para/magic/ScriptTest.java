package com.words.para.magic;

import com.words.para.ScriptTestUtils;
import com.words.para.TestApplicationConfiguration;
import com.words.para.entity.Script;
import com.words.para.scripts.BareillyKiBarfi;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static com.words.para.ScriptTestUtils.createScript;

@SpringBootTest(
    properties = "spring.main.allow-bean-definition-overriding=true",
    classes = {TestApplicationConfiguration.class})
@WebAppConfiguration
@AutoConfigureMockMvc
public class ScriptTest {

  @Autowired
  private WebApplicationContext fAppContext;
  protected MockMvc fMockMvc;

  @BeforeEach
  void setUp() {
    fMockMvc = MockMvcBuilders.webAppContextSetup(fAppContext).build();
  }

  @Test
  void testBareillyKiBarfi() throws Exception {
    final Script fScript = new Script();
    BareillyKiBarfi bareillyKiBarfi = new BareillyKiBarfi();
    fScript.addScene(bareillyKiBarfi.scene1());
    fScript.addScene(bareillyKiBarfi.scene2());
    fScript.addScene(bareillyKiBarfi.scene3());
    fScript.addScene(bareillyKiBarfi.scene4());
    System.out.println(fScript.generate());
  }

  @Test
  void testRangDeBasanti() throws Exception {
    String contentAsString = createScript(fMockMvc,"rang_de_basanti.json");
    System.out.println(contentAsString);
  }
}
