package pl.java.scalatech.controller.headers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/headSess")
public class HeaderSessController {

    
    @RequestMapping("/displayHeaderInfo")
    public String  displayHeaderInfo(@CookieValue("JSESSIONID") String cookie) {
     return " cookie : "+cookie;
    }
    
    @RequestMapping("/displayHeaderInfo2")
    public String  displayHeaderInfo(@RequestHeader("Accept-Encoding") String encoding,
            @RequestHeader("Keep-Alive") long keepAlive) {
        return " accept-encoding :  "+ encoding + "  keep-alive  "+ keepAlive;
    }
    
    
    
}
