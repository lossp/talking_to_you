package lossp.servicesImp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lossp.handler.ServerHandlerInitializer;
import lossp.proto.RequestProto;
import lossp.services.Server;
import lossp.session.SessionHolder;
import lossp.valueObject.P2PMessageRequestVO;
import lossp.valueObject.P2PMessageResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

@Component
public class ServerImp implements Server {
    Logger logger = LoggerFactory.getLogger(ServerImp.class);

    @Value("${server.netty.port}")
    private int port;

    // 定义主从模型，根据的是Reactor模型，NioEventLoopGroup维护的是事件循环
    private EventLoopGroup mainReactor = new NioEventLoopGroup(1);
    private EventLoopGroup subReactor = new NioEventLoopGroup();
    private boolean isRunning = false;

    /**
     * 启动服务，并且将服务注册至注册中心中，注册中心由route模块控制
     */
    @Override
    @PostConstruct
    public void start() throws InterruptedException{
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainReactor, subReactor)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ServerHandlerInitializer());
        ChannelFuture future = serverBootstrap.bind().sync();
        if (future.isSuccess()) {
            isRunning = true;
            logger.info("Successfully connect to the port: " + port);
        }
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * 关闭服务，关闭主处理器(Reactor)，其次关闭子处理器(Reactor)
     */
    @Override
    @PreDestroy
    public void stop() {
        mainReactor.shutdownGracefully().syncUninterruptibly();
        subReactor.shutdownGracefully().syncUninterruptibly();
        logger.info("Server is closed");
    }

    @Override
    public P2PMessageResponseVO sendP2PMessage(P2PMessageRequestVO p2PMessageRequestVO) {
        String result = SessionHolder.printChannelMap();
        logger.info(result);
        NioSocketChannel nioSocketChannel = SessionHolder.getChannel(p2PMessageRequestVO.getUserId());
        // 构造protobuf
        RequestProto.Request.Builder msg = RequestProto.Request.newBuilder();
        msg.setRequestId((int) p2PMessageRequestVO.getReceivedUserId().longValue());
        msg.setUserId((int) p2PMessageRequestVO.getUserId().longValue());
        msg.setMessage(p2PMessageRequestVO.getMessage());
        msg.setType("MSG");


        if (nioSocketChannel == null) throw new IllegalArgumentException("该用户对应的channel不存在");
        ChannelFuture future = nioSocketChannel.writeAndFlush(msg);
        future.addListener((ChannelFutureListener) channelFuture -> logger.info("server push msg:[{}]", p2PMessageRequestVO.toString()));
        return new P2PMessageResponseVO();
    }


    // 主要由服务器来推送消息
    public void sendMessage(P2PMessageRequestVO p2PMessageRequestVO) {
        NioSocketChannel socketChannel = SessionHolder.getChannel(p2PMessageRequestVO.getUserId());
        if (socketChannel == null) {
            logger.info("client {} offline!", p2PMessageRequestVO.getUserId());
        }
        RequestProto.Request request = RequestProto.Request.newBuilder()
                .setRequestId((int) p2PMessageRequestVO.getUserId().longValue())
                .setMessage("测试发送消息")
                .setType("MSG")
                .build();
        ChannelFuture future = socketChannel.writeAndFlush(request);
        future.addListener((ChannelFutureListener) channelFuture -> logger.info("Server push message: [{}]", p2PMessageRequestVO.toString()));
    }

}
