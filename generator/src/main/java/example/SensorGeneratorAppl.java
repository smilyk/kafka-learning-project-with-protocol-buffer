package example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SensorGeneratorAppl {
    private static long timeout = 120000;

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =
                SpringApplication.run(SensorGeneratorAppl.class, args);
        try {
            Thread.sleep(timeout);
            ctx.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
