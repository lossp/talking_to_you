package lossp.controller;

import lossp.service.ClientServerCenter;
import lossp.service.RouteRequest;
import lossp.valueObject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientServerController {
    Logger logger = LoggerFactory.getLogger(ClientServerController.class);

    @Autowired
    private ClientServerCenter clientServerImp;

    @Autowired
    private RouteRequest routeRequest;


    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
    public BaseResponse<NULLBody> sendMessage(@RequestBody StringRequestVO stringRequestVO) throws Exception {
        BaseResponse<NULLBody> response = new BaseResponse<>();
        if (!clientServerImp.getConnectServer()) throw new IllegalAccessException("没有连接到服务器，无法发送消息");
        clientServerImp.sendMessage(stringRequestVO.getMessage(), stringRequestVO.getReceiveUserId());
        response.setMessage("message sent successfully");
        response.setCode("SUCCESS");
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public BaseResponse<NULLBody> login(@RequestBody LoginRequestVO loginRequestVO) throws Exception {
        logger.info(loginRequestVO.toString());
        BaseResponse<NULLBody> response = new BaseResponse<>();
        ServerResponseVO serverInfo = clientServerImp.userLogin(loginRequestVO.getUsername(), loginRequestVO.getUserId());
        logger.info(serverInfo.toString());
        response.setMessage("login successfully");
        response.setCode("SUCCESS");
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/groupMessage")
    public BaseResponse<NULLBody> sendGroupMessage(@RequestBody SendMessageRequestVO sendMessageRequestVO) {
        BaseResponse<NULLBody> response = new BaseResponse<>();
        GroupMessageRequestVO groupMessageRequestVO  = new GroupMessageRequestVO(sendMessageRequestVO.getUserId(), sendMessageRequestVO.getMessage());
        routeRequest.sendGroupMessage(groupMessageRequestVO);
        response.setMessage("group message sent successfully");
        response.setCode("SUCCESS");
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/p2pMessage")
    public BaseResponse<NULLBody> sendP2PMessage(@RequestBody P2PMessageRequestVO p2PMessageRequestVO) {
        logger.info("p2PMessageRequestVO = [{}]", p2PMessageRequestVO);
        BaseResponse<NULLBody> response = new BaseResponse<>();
        try {
            routeRequest.sendP2PMessage(p2PMessageRequestVO);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("p2p message sent failed");
            response.setMessage("p2p message sent failed");
            response.setCode("FAILED");
            return response;
        }

        response.setMessage("p2p message sent successfully");
        response.setCode("SUCCESS");
        return response;
    }
}
