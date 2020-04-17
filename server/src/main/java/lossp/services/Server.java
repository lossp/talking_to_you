package lossp.services;

import lossp.valueObject.P2PMessageRequestVO;
import lossp.valueObject.P2PMessageResponseVO;

public interface Server {
    // 停止服务
    public void stop();

    // 启动服务
    public void start() throws Exception;

    // 判断服务是否在运行中
    public boolean isRunning();


    // 转发消息
    public P2PMessageResponseVO sendP2PMessage(P2PMessageRequestVO sendMessageRequestVO);

}
