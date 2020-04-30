package lossp.controllers;

import lossp.services.Server;
import lossp.valueObject.P2PMessageRequestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;


@RestController
public class ServerController {
    Logger logger = LoggerFactory.getLogger(ServerController.class);

    @Autowired
    Server serverImp;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String hello() {
        logger.info("Entering");
        logger.info("Server status: " + serverImp.isRunning());
        return "Hello";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/sendMessage")
    public String sendMessage(@RequestBody P2PMessageRequestVO p2PMessageRequestVO) {
        logger.info("p2p message request = [{}]", p2PMessageRequestVO);
        try {
            serverImp.sendP2PMessage(p2PMessageRequestVO);
        } catch (NoSuchElementException e) {
            e.printStackTrace();
            return "CLIENT_NOT_FOUND";
        }
        return "OK";
    }
}
