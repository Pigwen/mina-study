package org.maodian.mina.ch09;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

public class ImageCodecFactory implements ProtocolCodecFactory {
  private final ProtocolEncoder encoder;
  private final ProtocolDecoder decoder;

  public ImageCodecFactory(boolean client) {
    if (client) {
      encoder = new ImageRequestEncoder();
      decoder = new ImageResponseDecoder();
    } else {
      encoder = new ImageResponseEncoder();
      decoder = new ImageRequestDecoder();
    }
  }

  public ProtocolEncoder getEncoder(IoSession session) throws Exception {
    // TODO Auto-generated method stub
    return encoder;
  }

  public ProtocolDecoder getDecoder(IoSession session) throws Exception {
    // TODO Auto-generated method stub
    return decoder;
  }

}
