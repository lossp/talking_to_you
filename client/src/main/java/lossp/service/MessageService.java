package lossp.service;

import io.netty.channel.socket.SocketChannel;
import lossp.message.Message;


public interface MessageService {
    public void sendLoginRequest(Long userId, String username);

    public void sendMessage(String message, Long userId, String username, Long receiveUserId);

    public void sendGroupMessage(Message message);

    public void close();

    public boolean checkMessage(String message);

    public boolean innerCommand(String message);
}
