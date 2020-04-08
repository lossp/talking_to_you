package lossp.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lossp.message.Message;
import lossp.message.MessageDecoder;
import lossp.message.MessageEncoder;

public class ServerHandlerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    public void initChannel(SocketChannel socketChannel) {
        ChannelPipeline channelPipeline = socketChannel.pipeline();
        channelPipeline.addLast(new MessageEncoder(Message.class));
        channelPipeline.addLast(new MessageDecoder(Message.class));
        channelPipeline.addLast(new ServerHandler());
//        socketChannel.pipeline().addLast(new ServerHandler());
    }

}
