package lossp.service;

import io.netty.channel.socket.SocketChannel;
import lossp.message.Message;


public interface MessageService {
    public void sendMessage(String message, SocketChannel socketChannel);

    public void sendGroupMessage(Message message, SocketChannel socketChannel);

    public void close();
}
