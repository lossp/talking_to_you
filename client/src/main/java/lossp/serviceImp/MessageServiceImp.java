package lossp.serviceImp;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;
import lossp.message.Message;
import lossp.proto.RequestProto;
import lossp.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageServiceImp implements MessageService {
    Logger logger = LoggerFactory.getLogger(MessageServiceImp.class);
    //TODO 需要加入客户信息

    @Override
    public void sendMessage(String message, SocketChannel socketChannel, Long userId, Long receiveUserId) {
        RequestProto.Request.Builder msg = RequestProto.Request.newBuilder();
        msg.setRequestId((int) receiveUserId.longValue());
        msg.setUserId((int) userId.longValue());
        msg.setMessage(message);
        msg.setType("MSG");

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
