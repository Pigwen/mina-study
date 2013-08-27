package org.maodian.mina.ch09;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

public class ImageRequestDecoder extends CumulativeProtocolDecoder {

  protected boolean doDecode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
      throws Exception {
    if (in.remaining() >= 12) {
      ImageRequest request = new ImageRequest(in.getInt(), in.getInt(), in.getInt());
      out.write(request);
      return true;
    } else {
      return false;
    }
  }

}
