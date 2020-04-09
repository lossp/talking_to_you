package lossp.controller;

import lossp.serviceImp.ClientServerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientServerController {
    @Autowired
    private ClientServerImp clientServerImp;

    @RequestMapping(method = RequestMethod.GET, value = "/client")
    public String hello() {
        Logger logger = LoggerFactory.getLogger(ClientServerController.class);
        logger.info("Enterring clientController");
//        clientServerImp.sendMessage();
        return "Client Hello";
    }
}
