package lossp.controller;


import lossp.service.AccountService;
import lossp.service.DiscoveryService;
import lossp.serviceImp.RegisterServiceImp;
import lossp.valueObject.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RouteController {
    Logger logger = LoggerFactory.getLogger(RouteController.class);

    @Autowired
    RegisterServiceImp routeServiceImp;

    @Autowired
    DiscoveryService discoveryServiceImp;

    @Autowired
    private AccountService accountService;

    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public void register() {
        logger.info("enter");
        routeServiceImp.register("test");
    }


    @RequestMapping(method = RequestMethod.GET, value = "/discovery")
    public void discovery() {
        logger.info("进入服务发现接口");
        String result = discoveryServiceImp.discovery("/service");
        System.out.println();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/p2pRoute")
    public BaseResponse<NULLBody> p2pRoute(@RequestBody P2PRequestVO p2pRequestVO) throws Exception{
        BaseResponse<NULLBody> response = new BaseResponse<>();
        // TODO 1. 获取推送消息的路由服务器信息
        ServerResponseVO serverResponseVO = accountService.loadServerByUserId(p2pRequestVO.getReceivedUserId());
        String url = "http://" + serverResponseVO.getIp() + ":" + serverResponseVO.getHttpPort() + "/sendMessage";
        // TODO 2. 推送消息
        ChatMessageRequestVO chatMessageRequestVO = new ChatMessageRequestVO(p2pRequestVO.getUserId(), p2pRequestVO.getMessage());
        accountService.messagePush(url,p2pRequestVO.getUserId(), chatMessageRequestVO );
        response.setCode("SUCCESS");
        response.setMessage("p2p发送消息成功");
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ServerResponseVO login(@RequestBody LoginRequestVO loginRequestVO) throws Exception {
        logger.info("Entering Route controller, login request is processing at the moment ... ... ...");
        BaseResponse<NULLBody> response = new BaseResponse<>();
        logger.info(loginRequestVO.toString());
        ServerResponseVO serverResponseVO = accountService.loadServerByUserId(loginRequestVO.getUserId());
        logger.info(serverResponseVO.toString());
        return serverResponseVO;
    }
}
