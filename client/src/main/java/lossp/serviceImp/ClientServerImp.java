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
import lossp.valueObject.ServerInfoResVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

@Component
public class ClientServerImp implements ClientServerCenter {
    Logger logger = LoggerFactory.getLogger(ClientServerImp.class);
    //TODO 测试用途，以后需要将寻址交由Route模块处理，同时channel同样需要route进行处理
//    private final String host = "127.0.0.1";
//    private final int port = 3030;
    private SocketChannel channel;
    private String userName;
    private Long userId;

    @Autowired
    private RouteRequest routeRequest;



//    public ClientServerImp(String host, int port) {
//        this.host = host;
//        this.port = port;
//    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    @PostConstruct
    public void start() throws Exception {
        // 登陆，获取可用的服务器
        ServerInfoResVO.ServerInfo serverInfo = userLogin();
        startClient(serverInfo);

    }


    public void startClient(ServerInfoResVO.ServerInfo serverInfo) throws Exception {
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
        //TODO 原来测试与Server交互的代码，目前仅仅注释掉
        MessageScanner messageScanner = new MessageScanner(channel);
        Thread thread = new Thread(messageScanner);
        thread.start();

    }

    @Override
    public void close() {
    }


    private ServerInfoResVO.ServerInfo userLogin() {
        LoginRequestVO loginRequestVO = new LoginRequestVO(userName, userId);
        ServerInfoResVO.ServerInfo serverInfo = null;
        try {
            serverInfo = routeRequest.getServer(loginRequestVO);
            return serverInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
