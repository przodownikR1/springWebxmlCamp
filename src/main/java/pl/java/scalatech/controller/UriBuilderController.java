package pl.java.scalatech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriTemplate;

import pl.java.scalatech.domain.User;

@Controller
public class UriBuilderController {
    @RequestMapping(method = RequestMethod.GET, value = "/uri/{id}/{name}")
    public @ResponseBody
    String getUri(@PathVariable("id") Long id, @PathVariable("name") String name) {
        String result = UriComponentsBuilder.fromPath("/user/{id}/").build().expand(id).encode().toString();
    
        String ut = new UriTemplate(User.class.getSimpleName()).expand(id).getPath();
        System.err.println(ut);
        return result;
    }
}
