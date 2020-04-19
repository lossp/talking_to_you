package lossp.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import okhttp3.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
// TODO 需要将ByteBuf指定为更加明确的消息VO，目前仅仅是填充逻辑代码，此部分需要优化
// TODO 此部分需要引入protobuf... 或者定义统一的消息接口。
public class ServerHandler extends SimpleChannelInboundHandler<ByteBuf> {
    Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private final MediaType mediaType = MediaType.parse("application/json");

    @Override
    protected void channelRead0(ChannelHandlerContext context, ByteBuf byteBuf) {
        logger.info("Receiving message = [{}]", byteBuf);
        // TODO 填充存入
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object message) {
        System.out.println("Server received:" + message);
        context.write(message);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        context.writeAndFlush(Unpooled.EMPTY_BUFFER);
//                .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
