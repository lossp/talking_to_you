package lossp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {
    @RequestMapping(method = RequestMethod.GET, value = "/")
    public String hello() {
        Logger logger = LoggerFactory.getLogger(Test.class);
        logger.info("Enterring");
        return "Hello";
    }
}
