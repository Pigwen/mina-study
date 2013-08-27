package org.maodian.mina.ch09;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ImageResponseEncoder extends ProtocolEncoderAdapter {

  public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
    ImageResponse response = (ImageResponse) message;
    byte[] bytes1 = getBytes(response.getImage1());
    byte[] bytes2 = getBytes(response.getImage2());
    IoBuffer buffer = IoBuffer.allocate(8 + bytes1.length + bytes2.length, false);
    buffer.putInt(bytes1.length).put(bytes1).putInt(bytes2.length).put(bytes2).flip();
    out.write(buffer);
  }

  private byte[] getBytes(BufferedImage image) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ImageIO.write(image, "PNG", baos);
    return baos.toByteArray();
  }
}
