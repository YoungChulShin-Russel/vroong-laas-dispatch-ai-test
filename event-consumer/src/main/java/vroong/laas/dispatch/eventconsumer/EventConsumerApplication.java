package vroong.laas.dispatch.eventconsumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(
    scanBasePackages = {
        "vroong.laas.dispatch.eventconsumer",
        "vroong.laas.dispatch.core",
        "vroong.laas.dispatch.infrastructure"
    }
)
public class EventConsumerApplication {

  public static void main(String[] args) {
    SpringApplication.run(EventConsumerApplication.class, args);
  }

}
