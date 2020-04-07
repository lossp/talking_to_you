package lossp.servicesImp;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lossp.services.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;

public class ServerImp implements Server {
    Logger logger = LoggerFactory.getLogger(ServerImp.class);
    private final int port;
    // 定义主从模型，根据的是Reactor模型，NioEventLoopGroup维护的是事件循环
    private EventLoopGroup mainReactor = new NioEventLoopGroup(1);
    private EventLoopGroup subReactor = new NioEventLoopGroup();
    private boolean isRunning = false;

    public ServerImp(int port) { this.port = port; }
    /**
     * 启动服务，并且将服务注册至注册中心中，注册中心由route模块控制
     */
    @Override
    public void start() throws InterruptedException{
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainReactor, subReactor)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel socketChannel) {
                        socketChannel.pipeline().addLast(new ServerHandler());
                    }
                });
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
    public void stop() {
        mainReactor.shutdownGracefully().syncUninterruptibly();
        subReactor.shutdownGracefully().syncUninterruptibly();
        logger.info("Server is closed");
    }

}
