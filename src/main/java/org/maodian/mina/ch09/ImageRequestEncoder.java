package org.maodian.mina.ch09;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class ImageRequestEncoder implements ProtocolEncoder {

  public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
    ImageRequest request = (ImageRequest) message;
    IoBuffer buffer = IoBuffer.allocate(12, false);
    buffer.putInt(request.getWidth()).putInt(request.getHeight())
        .putInt(request.getNumberOfChars()).flip();
    out.write(buffer);
  }

  public void dispose(IoSession session) throws Exception {

  }

}
