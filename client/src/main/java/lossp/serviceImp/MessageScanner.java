package lossp.serviceImp;

import io.netty.channel.socket.SocketChannel;
import lossp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class MessageScanner implements Runnable {
    private MessageService messageService;
    private SocketChannel socketChannel;

    public MessageScanner(SocketChannel socketChannel) {
        this.messageService = new MessageServiceImp();
        this.socketChannel = socketChannel;
    }

    Logger logger = LoggerFactory.getLogger(MessageScanner.class);
    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String msg = in.nextLine();
            messageService.sendMessage(msg, socketChannel);
        }
    }
}
