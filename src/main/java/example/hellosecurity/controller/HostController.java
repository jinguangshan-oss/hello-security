package example.hellosecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostController {

    @RequestMapping(path = "/admin/host", method = RequestMethod.GET)
    String hello() {
        return "hello";
    }

    @RequestMapping(path = "/user/host", method = RequestMethod.GET)
    String other() {
        return "hello other";
    }

}
