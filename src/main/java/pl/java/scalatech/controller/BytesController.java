package pl.java.scalatech.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Controller
public class BytesController {
    
    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private org.springframework.core.io.Resource xmlExample;
    
    @SuppressWarnings("resource")
    @RequestMapping(value = "/user/{id}/pricture", method = RequestMethod.GET, headers = "Accept=image/jpeg, image/jpg, image/png, image/gif")
    public @ResponseBody
    byte[] getPhoto(@PathVariable("id") Long id) {

        try {
            InputStream is = this.getClass().getResourceAsStream("/image/compass.jpg");

            BufferedImage img = ImageIO.read(is);

            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            ImageIO.write(img, "jpg", bao);

            log.debug("Retrieving photo as byte array image");
            return bao.toByteArray();

        } catch (IOException e) {
            log.error("{}", e);
            throw new RuntimeException(e);
        }
    }

    @RequestMapping(value = "/getphoto", method = RequestMethod.GET)
    public void getPhoto(@RequestParam("id") Long id, HttpServletResponse response) {
        log.debug("Retrieve photo with id: " + id);

        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.IMAGE_JPEG);

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(acceptableMediaTypes);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        Map<String, Long> map = new HashMap<>();
        map.put("id", id);
        ResponseEntity<byte[]> result = restTemplate.exchange("http://localhost:8080/cars/carPicture/{id}", HttpMethod.GET, entity, byte[].class, map);

        // Display the image
        Writer.write(response, result.getBody());
    }
}
