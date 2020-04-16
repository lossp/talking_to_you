package lossp.controller;

import lossp.service.ClientServerCenter;
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


    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
    public BaseResponse<NULLBody> sendMessage(@RequestBody StringRequestVO stringRequestVO) throws Exception {
        BaseResponse<NULLBody> response = new BaseResponse<>();
        clientServerImp.start();
        clientServerImp.sendMessage(stringRequestVO.getMessage());

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
}
