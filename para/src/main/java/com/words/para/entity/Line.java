package com.words.para.entity;

import org.apache.commons.text.WordUtils;

public class Line {

  private Content fContent;
  private String text;

  public Line(final Content content) {
    fContent = content;
    text = "";
  }

  public Line(final Content content, final String text) {
    this.fContent = content;
    this.text = text;
  }

  public Content getPadding() {
    return fContent;
  }

  public String getText() {
    return text;
  }

  public void print() {
    String wrap = WordUtils.wrap(getText(), getPadding().getLength());
    wrap = wrap.replace("\n", "\n" + initialSpace());
    wrap = initialSpace() + wrap;
    System.out.println(wrap);
  }

  public String initialSpace() {
    return spaces(padding());
  }

  public int padding() {
    return getPadding().getPadding();
  }

  public String spaces(int chars) {
    return " ".repeat(chars);
  }
}
