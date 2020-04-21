package lossp.handler;

import io.netty.channel.*;
import lossp.proto.RequestProto;
import okhttp3.MediaType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ChannelHandler.Sharable
// TODO 需要将ByteBuf指定为更加明确的消息VO，目前仅仅是填充逻辑代码，此部分需要优化
// TODO 此部分需要引入protobuf... 或者定义统一的消息接口。
public class ServerHandler extends SimpleChannelInboundHandler<RequestProto.Request> {
    Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private final MediaType mediaType = MediaType.parse("application/json");

    @Override
    protected void channelRead0(ChannelHandlerContext context, RequestProto.Request in) {
        logger.info("Receiving message = [{}]", in.getMessage());
        // TODO 填充存入
    }

    @Override
    public void channelRead(ChannelHandlerContext context, Object message) {
        RequestProto.Request in = (RequestProto.Request) message;
        System.out.println("Server received:" + in.getMessage());
        context.write(in);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
