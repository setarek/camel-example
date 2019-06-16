package example;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class TransferFile {

    public static void main(String[] args) throws Exception {
        // create camel context
        CamelContext context = new DefaultCamelContext();
        try {
            // add routes for transfer file
            context.addRoutes(new RouteBuilder(){
                @Override
                public void configure() throws Exception {
                    from("file:/home/setarek/file1").to("file:/home/setarek/docs/file2");
                }
            });
            context.start();
            Thread.sleep(2000);

        }finally {
            context.stop();

        }
    }
}
