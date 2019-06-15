package example;

import org.apache.activemq.camel.component.ActiveMQComponent;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class HelloWorldExample {
    public static void main(String[] args) throws Exception {
        // create camel context
        CamelContext context = new DefaultCamelContext();
        try {
            // add JSM component to camel context
            context.addComponent("activemq", ActiveMQComponent.activeMQComponent("vm://localhost?broker.persistent=false"));
            // build route
            // listen to ActiveMQ then send message to standard output
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() throws Exception {
                    from("activemq:queue:testx.queue")
                            .to("stream:out");

                }
            });
            ProducerTemplate template = context.createProducerTemplate();
            context.start();
            template.sendBody("activemq:testx.queue", "Hello Java World");
            Thread.sleep(500);
        } finally {
            context.stop();
        }
    }
}