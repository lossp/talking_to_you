package lossp;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lossp.message.Message;

import java.util.Date;

@ChannelHandler.Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<Message> {
    @Override
    public void channelActive(ChannelHandlerContext context) {
        Message message = new Message();
        message.setUserId(12344);
        message.setMessage("tell me about it");
        message.setTime(new Date());
        message.setDelay(10);
        System.out.println(message);
        context.writeAndFlush(message);
    }

    @Override
    public void channelRead0(ChannelHandlerContext context, Message in) {
        System.out.println("Receive: " + in);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }

}
