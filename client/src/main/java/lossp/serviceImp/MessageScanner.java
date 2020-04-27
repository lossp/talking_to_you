package lossp.serviceImp;

import io.netty.channel.socket.SocketChannel;
import lossp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MessageScanner implements Runnable {
    private MessageService messageService;
    private Long userId;
    private Long receiveUserId;
    private String username;

    public MessageScanner(SocketChannel socketChannel, Long userId, String username, Long receiveUserId) {
        this.messageService = new MessageServiceImp(socketChannel);
        this.userId = userId;
        this.receiveUserId = receiveUserId;
        this.username = username;
    }

    Logger logger = LoggerFactory.getLogger(MessageScanner.class);
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String msg = in.nextLine();
            if (msg.equals("LOSSP")) break;
            messageService.sendMessage(msg, userId, username, receiveUserId);
        }
    }
}
