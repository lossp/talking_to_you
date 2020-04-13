package lossp.service;

import lossp.valueObject.GroupMessageRequestVO;
import lossp.valueObject.LoginRequestVO;
import lossp.valueObject.P2PMessageRequestVO;
import lossp.valueObject.ServerInfoResVO;

public interface RouteRequest {
    public void sendP2PMessage(P2PMessageRequestVO p2PMessageRequestVO) throws Exception;

    public void sendGroupMessage(GroupMessageRequestVO groupMessageRequestVO);

    public ServerInfoResVO.ServerInfo getServer(LoginRequestVO loginReqVO) throws Exception;

    public void offLine();

}
