package lossp.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lossp.message.Message;
import lossp.proto.RequestProto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<RequestProto.Request> {
    Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    @Override
    public void channelActive(ChannelHandlerContext context) {
        logger.info("客户端启动中...");
        context.writeAndFlush("客户端连接中");
    }

    @Override
    public void channelRead0(ChannelHandlerContext context, RequestProto.Request in) { logger.info("Receive: = " + in); }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object event) { logger.info("客户触发事件"); }

}
