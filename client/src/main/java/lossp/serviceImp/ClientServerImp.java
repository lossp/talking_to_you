package lossp.serviceImp;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lossp.handler.ClientHandlerInitializer;
import lossp.service.ClientServerCenter;
import lossp.service.MessageService;
import lossp.service.RouteRequest;
import lossp.valueObject.LoginRequestVO;


import lossp.valueObject.ServerResponseVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

@Component
public class ClientServerImp implements ClientServerCenter {
    Logger logger = LoggerFactory.getLogger(ClientServerImp.class);
    private SocketChannel channel;
    private Long userId;
    private String username;
    private boolean connectServer = false;

    @Autowired
    private RouteRequest routeRequest;

    private ServerResponseVO serverResponseVO;

    @Override
    public boolean getConnectServer() { return connectServer; }


    private void startClient(ServerResponseVO serverInfo) throws Exception {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        logger.info("连接中: ip = " + serverInfo.getIp() + " : " + serverInfo.getServerPort());
        bootstrap.group(eventLoopGroup)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(serverInfo.getIp(), serverInfo.getServerPort()))
                .handler(new ClientHandlerInitializer());
        // TODO this line here is very very important
        ChannelFuture future = bootstrap.connect().sync();
        channel = (SocketChannel) future.channel();
        connectServer = true;
        sendLoginRequest();
    }

    private void sendLoginRequest() {
        MessageService messageService = new MessageServiceImp(channel, routeRequest);
        messageService.sendLoginRequest(userId, username);
    }

    @Override
    public void sendMessage(String message, Long receiveUserId) {
        MessageScanner messageScanner = new MessageScanner(channel, userId, username, receiveUserId, routeRequest);
        Thread thread = new Thread(messageScanner);
        thread.start();
    }

    @Override
    public void logout() {

    }


    public ServerResponseVO userLogin(String userName, Long userId) {
        LoginRequestVO loginRequestVO = new LoginRequestVO(userName, userId);
        ServerResponseVO serverInfo = null;
        try {
            serverInfo = routeRequest.getServer(loginRequestVO);
            serverResponseVO = serverInfo;
            this.userId = userId;
            this.username = userName;
            startClient(serverInfo);
            return serverInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
