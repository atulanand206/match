package com.words.para.magic;

import com.words.para.entity.Scene;
import com.words.para.entity.Script;
import org.junit.jupiter.api.Test;
import static com.words.para.entity.SceneType.EXT;
import static com.words.para.entity.SceneType.INT;
import static com.words.para.entity.SceneType.INT_EXT;
import static com.words.para.entity.content.Dialogue.additionalSplitString;

public class ScriptTest {

  private final Script fScript = new Script();

  @Test
  void testPrintScene() {
    fScript.addScene(scene1());
    fScript.addScene(scene2());
    fScript.addScene(scene3());
    fScript.addScene(scene4());
    System.out.println(fScript.generate());
  }

  public Scene scene1() {
    Scene scene = new Scene(1, EXT, "BITTI TERRACE", "WINTER EARLY MORNING");
    scene.addInfo(
        "Top shot of small town Bareilly. It’s early winter. Little fog. Smoke in a few places.");
    scene.addDialogue("FATHER",
        "Arre Pilibhit/Bijauria jaana hai" + additionalSplitString()
            + "mithai deliver karne. Bina fresh" + additionalSplitString()
            + "hue nikle aur raaste mein aa gayi toh gajab ho jaayega.",
        "", "Shoot", "DRama");
    scene.addDialogue("7TH JUROR",
        "This better be fast. I got tickets to a ball game tonight. Yankees -- Cleveland. We got this new kid pitching, Modjelewski, or whatever his ame is. He's a bull, this kid."
            + additionalSplitString() + "Shhooooom. A real jug handle."
            + additionalSplitString() + "You're quite a ball fan, aren't you?"
            + additionalSplitString() + "Where do you want us to sit?",
        "to the 2nd Juror",
        "He shoots his hand forward and out to indicate the path of a curve ball",
        "no reaction at all from the 2nd Juror", "He turns to the Foreman");
    return scene;
  }

  public Scene scene2() {
    Scene scene = new Scene(2, INT_EXT, "BITTI HOUSE", "EARLY MORNING");
    scene.addInfo(
        "Camera crosses empty terraces and moves inside a room to stop over a girl sleeping with her face covered with bedsheet.");
    scene.addInfo(
        "Camera travels back from the bedsheet covered girl to the woman in the courtyard on ground floor.");
    scene.addInfo(
        "We see top angle shot of a woman praying around a Tulsi plant. It is placed next to the prayer room.");
    return scene;
  }

  public Scene scene3() {
    Scene scene = new Scene(3, INT, "BITTI COURTYARD", "CONTINUOUS");
    scene.addInfo(
        "Mother, around 45 years old, is praying around the Tulsi plant, ringing the bell, already bathed.");
    scene.addInfo(
        "Father, around 50 years old, walks out of a room in a sweater and pyjama, looking a bit sleepy.");
    scene.addDialogue("FATHER", "Susheela, O Susheela. Arre hamaari cigarette dekhi hai kya?");
    scene.addInfo(
        "Mother makes her eyes bigger and rings the bell louder to indicate that she is praying. Father shows her an empty cigarette packet.");
    scene.addDialogue("FATHER", "Arre kal raat isme ek baaki thi.");
    scene.addInfo("Mother continues to pray. Father looks hassled.");
    scene.addDialogue("FATHER", "Iske bina subah utarti bhi toh nahi hai.");
    scene.addInfo("Mother makes a face as she prays. Father requests her.");
    scene.addDialogue("FATHER",
        "Arre Pilibhit/Bijauria jaana hai mithai deliver karne. Bina fresh hue nikle aur raaste mein aa gayi toh gajab ho jaayega.");
    scene.addDialogue("FATHER",
        "Arre Pilibhit/Bijauria jaana hai ----- mithai deliver karne. Bina fresh ----- hue nikle aur raaste mein aa gayi toh gajab ho jaayega.",
        "", "Shoot", "DRama");
    scene.addInfo(
        "Mother makes a face and requests heavens to pardon her husband as she continues to pray.");
    scene.addDialogue("FATHER", "Itni subah kahin milegi bhi nahi. Bitti se maang ke laa do na.");
    scene.addInfo("Mother looks scandalized and finishes her prayer quickly.");
    scene.addDialogue("MOTHER", "Bitti ke paas kahaan se aayegi cigarette?",
        "irritated, surprised");
    scene.addDialogue("FATHER", "Arre wohh peeti hai.");
    scene.addInfo("Mother is scandalized as she looks at the father suspiciously.");
    scene.addInfo("c/u of Father nods to indicate that it's the truth.");
    scene.addDialogue("FATHER", "Maang lo bhai, urgent hai.");
    scene.addInfo("Cut to Mother grumbles as she moves towards terrace.");
    scene.addDialogue("MOTHER", "Bas ek cigarette ki kami reh gayi thi laundiya mein.");
    scene.addInfo("Cut to Father taunts from behind.");
    scene.addDialogue("FATHER", "Kuch din aur ruk jaao, desi daru bhi milegi uske paas.");
    return scene;
  }

  public Scene scene4() {
    Scene scene = new Scene(4, INT, "BITTI ROOM", "CONTINUOUS");
    scene.addInfo(
        "Mother walks up to a girl sleeping with her face covered with rajaai. She shakes her and tries to wake her up.");
    scene.addDialogue("MOTHER", "Bitti, ai Bitti.");
    scene.addInfo("Bitti replies without getting up.");
    scene.addDialogue("BITTI", "Kya kar rahe ho jaanu.");
    scene.addInfo("Mother pulls the bedsheet.");
    scene.addDialogue("MOTHER", "Jaanu ki bachchi!!!");
    scene.addInfo(
        "Cut to Bitti’s face as she struggles to opens her eyes and see her mother in daylight. She is a young girl around 28.");
    return scene;
  }

}
