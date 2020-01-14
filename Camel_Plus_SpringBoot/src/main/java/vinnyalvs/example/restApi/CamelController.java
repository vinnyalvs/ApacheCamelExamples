package vinnyalvs.example.restApi;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.camel.builder.Builder.constant;

@RestController
public class CamelController {
 @Autowired
 ProducerTemplate producerTemplate;
    @RequestMapping(value = "/")
    public void startCamel() {
        producerTemplate.sendBody("direct:hello", "Calling with Spring Boot Rest Controller");
    }

    @RequestMapping(value = "/user")
    public void transcriptionRoute(@RequestParam String name){
        producerTemplate.sendBody("direct:user", name);

    }
}