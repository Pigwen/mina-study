package org.maodian.mina.ch09;

public class ImageRequest {
  private final int width;
  private final int height;
  private final int numberOfChars;

  public ImageRequest(int width, int height, int numberOfChars) {
    super();
    this.width = width;
    this.height = height;
    this.numberOfChars = numberOfChars;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getNumberOfChars() {
    return numberOfChars;
  }

}
