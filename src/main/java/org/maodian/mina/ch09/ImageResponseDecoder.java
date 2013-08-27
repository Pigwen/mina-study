package org.maodian.mina.ch09;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ImageResponseDecoder extends CumulativeProtocolDecoder {
  private static final String DECODER_STATE_KEY = ImageResponseDecoder.class.getName() + ".STATE";
  public static final int MAX_IMAGE_SIZE = 5 * 1024 * 1024;

  private static class DecoderState {
    BufferedImage image1;
  }

  public boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
      throws Exception {
    DecoderState state = (DecoderState) session.getAttribute(DECODER_STATE_KEY);
    if (state == null) {
      state = new DecoderState();
      session.setAttribute(DECODER_STATE_KEY, state);
    }

    if (state.image1 == null) {
      if (in.prefixedDataAvailable(4, MAX_IMAGE_SIZE)) {
        state.image1 = readImage(in);
      } else {
        return false;
      }
    }

    if (state.image1 != null) {
      if (in.prefixedDataAvailable(4, MAX_IMAGE_SIZE)) {
        BufferedImage image = readImage(in);
        ImageResponse response = new ImageResponse(state.image1, image);
        out.write(response);
        state.image1 = null;
        return true;
      } else {
        return false;
      }
    }
    return false;
  }

  private BufferedImage readImage(IoBuffer in) throws IOException {
    int length = in.getInt();
    byte[] bytes = new byte[length];
    in.get(bytes);
    ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
    return ImageIO.read(bais);
  }
}
