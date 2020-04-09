package lossp.serviceImp;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;
import lossp.message.Message;
import lossp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class MessageServiceImp implements MessageService {
    Logger logger = LoggerFactory.getLogger(MessageServiceImp.class);
    //TODO 需要加入客户信息


    @Override
    public void sendMessage(String message, SocketChannel socketChannel) {
        //TODO 仅仅测试数据
        Message msg = new Message();
        msg.setTime(new Date());
        msg.setDelay(10);
        msg.setMessage(message);
        msg.setUserId(123444);

        ChannelFuture f = socketChannel.writeAndFlush(msg);
        f.addListener((ChannelFutureListener) channelFuture -> logger.info("客户端手动发消息成功={}", msg));
    }

    @Override
    public void sendGroupMessage(Message message, SocketChannel channel) {

    }


    @Override
    public void close() {

    }
}
