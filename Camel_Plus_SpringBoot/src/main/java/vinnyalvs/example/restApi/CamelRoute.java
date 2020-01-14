package vinnyalvs.example.restApi;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        //Routes from producer template.
        from("direct:hello")
                .log("${body}").
                setBody().constant("Hello World");


        from("direct:user").
                log("My user name is ${body}").
         to("mock:consumer");

    }
}



