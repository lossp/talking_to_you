package lossp.serviceImp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lossp.handler.ClientHandlerInitializer;
import lossp.service.ClientServerCenter;
import lossp.service.RouteRequest;
import lossp.valueObject.LoginRequestVO;


import lossp.valueObject.ServerResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class ClientServerImp implements ClientServerCenter {
    Logger logger = LoggerFactory.getLogger(ClientServerImp.class);
    private SocketChannel channel;

    @Value("${server.client.username}")
    private String userName;
    @Value("${server.client.userId}")
    private Long userId;

    @Autowired
    private RouteRequest routeRequest;

    private ServerResponseVO serverResponseVO;

    @Override
    public void start() {
        try {
            if (serverResponseVO == null) throw new IllegalArgumentException("Server info needs to initialized first, please login in");
            startClient(serverResponseVO);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error("连接客户端失败");
        }

    }


    private void startClient(ServerResponseVO serverInfo) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(serverInfo.getIp(), serverInfo.getHttpPort()))
                .handler(new ClientHandlerInitializer());
        // TODO this line here is very very important
        ChannelFuture future = bootstrap.connect().sync();
        channel = (SocketChannel) future.channel();
//        this.sendMessage();
    }

    @Override
    public void sendMessage(String message) {
        MessageScanner messageScanner = new MessageScanner(channel);
        Thread thread = new Thread(messageScanner);
        thread.start();
    }

    @Override
    public void close() {
    }


    private ServerResponseVO userLogin() {
        LoginRequestVO loginRequestVO = new LoginRequestVO(this.userName, this.userId);
        ServerResponseVO serverInfo = null;
        try {
            serverInfo = routeRequest.getServer(loginRequestVO);
            return serverInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ServerResponseVO userLogin(String userName, Long userId) {
        LoginRequestVO loginRequestVO = new LoginRequestVO(userName, userId);
        ServerResponseVO serverInfo = null;
        try {
            serverInfo = routeRequest.getServer(loginRequestVO);
            serverResponseVO = serverInfo;
            return serverInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
