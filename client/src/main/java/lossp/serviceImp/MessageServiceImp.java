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
    private SocketChannel socketChannel;

    public MessageServiceImp(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void sendMessage(String message, Long userId, String username, Long receiveUserId) {
        RequestProto.Request.Builder msg = RequestProto.Request.newBuilder();
        msg.setRequestId((int) receiveUserId.longValue());
        msg.setUserId((int) userId.longValue());
        msg.setMessage(message);
        msg.setUsername(username);
        msg.setType("MSG");

        ChannelFuture future = socketChannel.writeAndFlush(msg);
        future.addListener((ChannelFutureListener) channelFuture -> logger.info("客户端手动发消息成功={}", msg));
    }

    @Override
    public void sendGroupMessage(Message message) {

    }

    @Override
    public void sendLoginRequest(Long userId, String username) {
        RequestProto.Request.Builder msg = RequestProto.Request.newBuilder();
        msg.setUserId((int) userId.longValue());
        // 登陆时候将username作为message，之后需要进行重构，单独增加username字段，目前为了功能，只能将就了
        // TODO 重构
        msg.setMessage("");
        msg.setUsername(username);
        // 没有指定用户，默认request id为0
        msg.setRequestId(0);
        msg.setType("LOGIN");
        ChannelFuture future = socketChannel.writeAndFlush(msg);
        future.addListener((ChannelFutureListener) channelFuture -> logger.info("客户端登陆成功 = {}", msg));

    }


    @Override
    public void close() {

    }
}
