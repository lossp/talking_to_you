package lossp.controller;


import lossp.service.DiscoveryService;
import lossp.serviceImp.RegisterServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {
    Logger logger = LoggerFactory.getLogger(ServiceController.class);

    @Autowired
    RegisterServiceImp routeServiceImp;

    @Autowired
    DiscoveryService discoveryServiceImp;

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
}
