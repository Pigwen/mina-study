package org.maodian.mina.ch09;

import java.awt.image.BufferedImage;

public class ImageResponse {
  private final BufferedImage image1;
  private final BufferedImage image2;

  public ImageResponse(BufferedImage image1, BufferedImage image2) {
    super();
    this.image1 = image1;
    this.image2 = image2;
  }

  public BufferedImage getImage1() {
    return image1;
  }

  public BufferedImage getImage2() {
    return image2;
  }

}
