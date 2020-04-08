package lossp.controllers;

import lossp.servicesImp.ServerImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServerController {
    @Autowired
    ServerImp serverImp;

    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String hello() {
        Logger logger = LoggerFactory.getLogger(ServerController.class);
        logger.info("Enterring");
        logger.info("Server status: " + serverImp.isRunning());
        return "Hello";
    }
}
