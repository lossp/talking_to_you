package lossp.service;

import lossp.valueObject.*;
import org.springframework.stereotype.Component;


public interface RouteRequest {
    public void sendP2PMessage(P2PMessageRequestVO p2PMessageRequestVO) throws Exception;

    public void sendGroupMessage(GroupMessageRequestVO groupMessageRequestVO);

    public ServerResponseVO getServer(LoginRequestVO loginReqVO) throws Exception;

    public void offLine();

}
