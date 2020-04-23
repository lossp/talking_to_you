package lossp.serviceImp;

import io.netty.channel.socket.SocketChannel;
import lossp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MessageScanner implements Runnable {
    private MessageService messageService;
    private SocketChannel socketChannel;
    private Long userId;
    private Long receiveUserId;

    public MessageScanner(SocketChannel socketChannel, Long userId, Long receiveUserId) {
        this.messageService = new MessageServiceImp();
        this.socketChannel = socketChannel;
        this.userId = userId;
        this.receiveUserId = receiveUserId;
    }

    Logger logger = LoggerFactory.getLogger(MessageScanner.class);
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        in.nextLine();
        while (true) {
            String msg = in.nextLine();
            if (msg.equals("LOSSP")) break;
            messageService.sendMessage(msg, socketChannel, userId, receiveUserId);
        }
    }
}
