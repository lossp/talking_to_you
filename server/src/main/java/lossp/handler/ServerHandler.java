package lossp.handler;

import io.netty.channel.*;
import io.netty.channel.socket.nio.NioSocketChannel;
import lossp.proto.RequestProto;
import lossp.session.SessionHolder;
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
        logger.info("Receiving message = [{}], type = [{}]", in.getMessage(), in.getType());
        if (in.getType().equals("LOGIN")) {
            SessionHolder.saveChannel(Long.valueOf(in.getRequestId()), (NioSocketChannel) context.channel());
            SessionHolder.saveSession(Long.valueOf(in.getRequestId()), in.getMessage());
            logger.info("client [{}] online success", in.getMessage());
        }

        if (in.getType().equals("PING")) {

        }

        context.write(in);
        // TODO 填充存入
    }




    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }
}
