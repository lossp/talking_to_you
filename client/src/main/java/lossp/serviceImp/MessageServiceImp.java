package lossp.serviceImp;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;
import lossp.message.Message;
import lossp.proto.RequestProto;
import lossp.service.MessageService;
import lossp.service.RouteRequest;
import lossp.valueObject.P2PMessageRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MessageServiceImp implements MessageService {
    Logger logger = LoggerFactory.getLogger(MessageServiceImp.class);
    //TODO 需要加入客户信息
    private SocketChannel socketChannel;
    private RouteRequest routeRequest;

    public MessageServiceImp(SocketChannel socketChannel, RouteRequest routeRequest) {
        this.socketChannel = socketChannel;
        this.routeRequest = routeRequest;
    }

    @Override
    public void sendMessage(String message, Long userId, String username, Long receiveUserId) {
        P2PMessageRequestVO p2PMessageRequestVO = new P2PMessageRequestVO(userId, receiveUserId, message);
        try {
            routeRequest.sendP2PMessage(p2PMessageRequestVO);
            logger.info("Sending...");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override
    public boolean checkMessage(String message) {
        if (message.isEmpty()) {
            return false;
        }
        return true;
    }

    @Override
    public boolean innerCommand(String message) {
        return true;
    }
}
