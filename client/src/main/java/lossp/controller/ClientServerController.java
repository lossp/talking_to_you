package lossp.controller;

import lossp.service.ClientServerCenter;
import lossp.serviceImp.ClientServerImp;
import lossp.valueObject.BaseResponse;
import lossp.valueObject.NULLBody;
import lossp.valueObject.StringRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientServerController {
    @Autowired
    private ClientServerCenter clientServerImp;

    @RequestMapping(method = RequestMethod.GET, value = "/client")
    public String hello() {
        Logger logger = LoggerFactory.getLogger(ClientServerController.class);
        logger.info("Enterring clientController");
//        clientServerImp.sendMessage();
        return "Client Hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
    public BaseResponse<NULLBody> sendMessage(@RequestBody StringRequestVO stringRequestVO) throws Exception {
        BaseResponse<NULLBody> response = new BaseResponse<>();
        // TODO 需要setUsername以及setUserId
        Long userId = new Long((long)1);
        clientServerImp.setUserId(userId);
        clientServerImp.setUserName("telleme");
        clientServerImp.start();
        clientServerImp.sendMessage(stringRequestVO.getMessage());

        response.setMessage("message sent successfully");
        response.setCode("SUCCESS");
        return response;
    }
}
