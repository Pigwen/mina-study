package org.maodian.mina.ch09;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

public class ImageServer {

  /**
   * @param args
   * @throws IOException
   */
  public static void main(String[] args) throws IOException {
    IoAcceptor acceptor = new NioSocketAcceptor();
    acceptor.getFilterChain().addLast("protocol",
        new ProtocolCodecFilter(new ImageCodecFactory(false)));
    acceptor.setHandler(new ImageServerIoHandler());
    acceptor.bind(new InetSocketAddress(33789));
  }

}
