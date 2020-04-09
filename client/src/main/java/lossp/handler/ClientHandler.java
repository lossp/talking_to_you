package lossp.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lossp.message.Message;

@ChannelHandler.Sharable
public class ClientHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    public void channelActive(ChannelHandlerContext context) {
        System.out.println("客户端启动中...");
        context.writeAndFlush("客户端连接中");
    }

    @Override
    public void channelRead0(ChannelHandlerContext context, Message in) {
        System.out.println("Receive: = " + in);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext context, Object event) {
        System.out.println("客户触发事件");

    }

}
