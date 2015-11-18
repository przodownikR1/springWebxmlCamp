package pl.java.scalatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.java.scalatech.controller.exception.BadRequestException;
import pl.java.scalatech.controller.exception.RecognitionException;

@Controller
public class ExceptionHandlerTestController {

    @RequestMapping(value = "/mvc/plaintext", method = RequestMethod.GET, headers = "Accept=text/plain")
    public @ResponseBody String plain() {
        return "a response body";
    }
    @RequestMapping(value = "/badrequestEx", method = RequestMethod.GET)
    public @ResponseBody String badRequestEx() throws BadRequestException {
        throw new BadRequestException("test bad request exception");
    }
    
    @RequestMapping(value = "/recordNotFound", method = RequestMethod.GET)
    public @ResponseBody String recordNotFound() throws RecognitionException {
        throw new RecognitionException("Record not found for user with uuid: " +12);
    }
}
