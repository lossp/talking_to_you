package lossp.handler;

import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import lossp.proto.RequestProto;
import lossp.session.SessionHolder;
import lossp.valueObject.UserInfo;
import okhttp3.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<RequestProto.Request> {
    Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private final MediaType mediaType = MediaType.parse("application/json");

    @Override
    protected void channelRead0(ChannelHandlerContext context, RequestProto.Request in) {
        logger.info("Receiving message = [{}], type = [{}], receive userId = [{}],  user id = [{}]", in.getMessage(), in.getType(), in.getRequestId(), in.getUserId());
        if (!in.getType().equals("PING")) {
            SessionHolder.saveChannel(Long.valueOf(in.getRequestId()), (NioSocketChannel) context.channel());
            SessionHolder.saveSession(Long.valueOf(in.getRequestId()), in.getMessage());
            logger.info("client [{}] online success", in.getUserId());
        }

        if (in.getType().equals("PING")) {

        }

        context.write(in);
        // TODO 填充存入
    }

    @Override
    public void channelInactive(ChannelHandlerContext context) throws Exception{
        logger.info("Client disconnecting ... ...");
        logger.info("current channel is [{}]", context.channel());
        logger.info("before Session map = [{}]", SessionHolder.printSessionMap());
        logger.info("before Channel map = [{}]", SessionHolder.printChannelMap());
        UserInfo userInfo = SessionHolder.getUserInfo((NioSocketChannel) context.channel());
        if (userInfo != null) {
            logger.info("client [{}] is offline", userInfo.getUsername());
            SessionHolder.removeSession(userInfo.getUserId());
            SessionHolder.removeChannle((NioSocketChannel) context.channel());
            logger.info("after Session map = [{}]", SessionHolder.printSessionMap());
            logger.info("after Channel map = [{}]", SessionHolder.printChannelMap());
        }
    }




    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
